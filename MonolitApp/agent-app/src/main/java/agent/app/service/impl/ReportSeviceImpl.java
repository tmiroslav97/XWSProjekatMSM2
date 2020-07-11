package agent.app.service.impl;


import agent.app.dto.carreq.SubmitReportDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.*;
import agent.app.repository.ReportRepository;
import agent.app.service.intf.*;
import agent.app.ws.client.RequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private PublisherUserService publisherUserService;

    @Autowired
    private AdRequestService adRequestService;

    @Autowired
    private RequestClient requestClient;

    @Autowired
    private AgentService agentService;

    @Autowired
    private CarService carService;

    @Autowired
    private RequestService requestService;

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
    public void submitReport(SubmitReportDTO submitReport, String email_agent) {
        AdRequest adRequest = adRequestService.findById(submitReport.getAdId());
        Float payAmount = 0F;
        Float km = 0F;

        if (adRequest.getDistanceLimitFlag().toString().equals("LIMITED")) {

            if (submitReport.getDistanceTraveled() - adRequest.getMileage() >= adRequest.getDistanceLimit()) {
                km = submitReport.getDistanceTraveled() - adRequest.getMileage();
                if (adRequest.getCdw())
                    payAmount = (submitReport.getDistanceTraveled() - adRequest.getMileage()) * adRequest.getPricePerKmCDW();
                else {
                    payAmount = (submitReport.getDistanceTraveled() - adRequest.getMileage()) * adRequest.getPricePerKm();
                }
            }
        }

        if (adRequest.getDiscount() != null) {
            payAmount = payAmount - (adRequest.getDiscount() / 100) * payAmount;
        }
        Invoice invoice = new Invoice();
        invoice.setAmount(payAmount);
        invoice.setEmail(submitReport.getEmail());
        invoiceService.save(invoice);

        Report report = new Report();
        report.setInvoice(invoice);
        report.setDescription(submitReport.getDescription());
        report.setDistanceTraveled(submitReport.getDistanceTraveled());
        report.setPublisherUser(publisherUserService.findByEmail(email_agent));
        this.save(report);

        adRequest.setReport(report);
        adRequestService.save(adRequest);

        Ad ad = adService.findById(adRequest.getAdId());
        Car car = ad.getCar();
        car.setMileage(submitReport.getDistanceTraveled());
        carService.editCar(car);
        //preko soapa-sinhornizovati sa mikroservisnom
        services.app.carrequestservice.model.Invoice invoiceSoap = new services.app.carrequestservice.model.Invoice();
        invoiceSoap.setAmount(invoice.getAmount());
        invoiceSoap.setEmail(invoice.getEmail());

        services.app.carrequestservice.model.Report reportSoap = new services.app.carrequestservice.model.Report();
        reportSoap.setDescription(report.getDescription());
        reportSoap.setAdId(ad.getMainId());
        reportSoap.setDistanceTraveled(report.getDistanceTraveled());
        reportSoap.setInvoice(invoiceSoap);
        String identifier = agentService.findByEmail(email_agent).getIdentifier();
        Request request = requestService.findById(submitReport.getRequestId());
        Long mainId = requestClient.submitReportRequest(request.getMainId(), email_agent, identifier, reportSoap);
        report.setMainId(mainId);
        report = this.save(report);
    }
}
