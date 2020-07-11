package services.app.carrequestservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.config.RabbitMQConfiguration;
import services.app.carrequestservice.dto.MileageUpdateDTO;
import services.app.carrequestservice.dto.PayDTO;
import services.app.carrequestservice.dto.carreq.SubmitReportDTO;
import services.app.carrequestservice.exception.NotFoundException;
import services.app.carrequestservice.model.Ad;
import services.app.carrequestservice.model.Invoice;
import services.app.carrequestservice.model.Report;
import services.app.carrequestservice.repository.ReportRepository;
import services.app.carrequestservice.service.intf.AdService;
import services.app.carrequestservice.service.intf.InvoiceService;
import services.app.carrequestservice.service.intf.ReportService;

import java.util.List;

@Service
public class ReportSeviceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override

    public Report findById(Long id) {
        return reportRepository.findById(id).orElseThrow(() -> new NotFoundException("Izvjestaj ne postoji"));

    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Report save(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void delete(Report report) {
        reportRepository.delete(report);
    }

    @Override
    public Integer deleteById(Long id) {
        Report report = this.findById(id);
        this.delete(report);
        return 1;
    }

    @Override
    public PayDTO payRent(Long requestId) {
        return null;
    }

    @Override
    public void submitReport(SubmitReportDTO submitReport, Long id_agent) {
        Ad ad = adService.findById(submitReport.getAdId());
        Float payAmount = 0F;
        if (ad.getDistanceLimitFlag().toString().equals("LIMITED")) {
            if (submitReport.getDistanceTraveled() - ad.getMileage() >= ad.getDistanceLimit()) {
                if (ad.getCdw())
                    payAmount = (submitReport.getDistanceTraveled() - ad.getMileage()) * ad.getPricePerKmCDW();
                else {
                    payAmount = (submitReport.getDistanceTraveled() - ad.getMileage()) * ad.getPricePerKm();
                }
            }
        }
        if (ad.getDiscount() != null) {
            payAmount = payAmount - (ad.getDiscount() / 100) * payAmount;
        }
        Invoice invoice = new Invoice();
        invoice.setAmount(payAmount);
        invoice.setEmail(submitReport.getEmail());
        invoiceService.save(invoice);

        Report report = new Report();
        report.setInvoice(invoice);
        report.setAdId(submitReport.getAdId());
        report.setDescription(submitReport.getDescription());
        report.setDistanceTraveled(submitReport.getDistanceTraveled());
        report.setPublisherUser(id_agent);
        this.save(report);

        ad.setReport(report);
        //komunikacija sa adService i adSearch service da se ostavi mileage, preko Ad mainId
        try {
            MileageUpdateDTO mileageUpdateDTO = MileageUpdateDTO.builder()
                    .adId(ad.getMainId())
                    .mileage(report.getDistanceTraveled())
                    .build();
            String mileageUpdateDTOStr = objectMapper.writeValueAsString(mileageUpdateDTO);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.UPDATE_MILEAGE_AD_QUEUE_NAME, mileageUpdateDTOStr);
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.UPDATE_MILEAGE_AD_SEARCH_QUEUE_NAME, mileageUpdateDTOStr);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
        adService.save(ad);
    }
}
