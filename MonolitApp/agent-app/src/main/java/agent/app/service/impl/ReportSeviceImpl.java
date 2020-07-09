package agent.app.service.impl;



import agent.app.dto.carreq.SubmitReportDTO;
import agent.app.exception.NotFoundException;
import agent.app.model.Ad;
import agent.app.model.AdRequest;
import agent.app.model.Invoice;
import agent.app.model.Report;
import agent.app.repository.ReportRepository;
import agent.app.service.intf.*;
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
       Ad ad = adService.findById(submitReport.getAdId());
//        AdRequest adRequest =  adRequestService.findById(submitReport.getAdId());
        Float payAmount = 0F;
        Float km = 0F;

       if(ad.getCar().getDistanceLimitFlag().toString().equals("LIMITED")){

           if(ad.getCar().getMileage()<=submitReport.getDistanceTraveled()){
               km = submitReport.getDistanceTraveled() - ad.getCar().getMileage();
               if(ad.getCar().getCdw())
                    payAmount = submitReport.getDistanceTraveled() * ad.getPriceList().getPricePerKmCDW();
                else{
                   System.out.println("Ogranicenje i nema cdw");
                   payAmount = submitReport.getDistanceTraveled() *  ad.getPriceList().getPricePerKm();

               }
           }
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

        AdRequest adRequest =  adRequestService.findById(submitReport.getAdId());
        adRequest.setReport(report);
        adRequestService.save(adRequest);


        ad.getCar().setMileage(km);
//       ad.setReport(report); //ne znam da li treba i ovo da se setuje?/
       adService.save(ad);
    }
}
