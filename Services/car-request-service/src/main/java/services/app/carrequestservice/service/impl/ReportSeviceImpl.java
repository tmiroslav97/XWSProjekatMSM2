package services.app.carrequestservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.dto.PayDTO;
import services.app.carrequestservice.dto.carreq.SubmitReportDTO;
import services.app.carrequestservice.exception.NotFoundException;
import services.app.carrequestservice.model.*;
import services.app.carrequestservice.repository.AdRepository;
import services.app.carrequestservice.repository.ReportRepository;
import services.app.carrequestservice.service.intf.AdService;
import services.app.carrequestservice.service.intf.InvoiceService;
import services.app.carrequestservice.service.intf.ReportService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportSeviceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private InvoiceService invoiceService;

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
        System.out.println("Id oglasa za izvjestaj: " + submitReport.getAdId());
       Float payAmount = 0F;
       if(ad.getDistanceLimitFlag().toString().equals("LIMITED")){
           if(ad.getDistanceLimit()<=submitReport.getDistanceTraveled()){
               if(ad.getCdw())
                    payAmount = (submitReport.getDistanceTraveled() -  ad.getDistanceLimit()) * ad.getPricePerKmCDW();
                else{
                   System.out.println("Ogranicenje i nema cdw");
                   payAmount = (submitReport.getDistanceTraveled() -  ad.getDistanceLimit()) * ad.getPricePerKm();

               }
           }
       }
        System.out.println(payAmount);

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
        adService.save(ad);
    }
}
