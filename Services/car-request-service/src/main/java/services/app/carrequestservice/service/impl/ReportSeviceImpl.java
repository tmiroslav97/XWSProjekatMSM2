package services.app.carrequestservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.app.carrequestservice.dto.PayDTO;
import services.app.carrequestservice.dto.carreq.SubmitReportDTO;
import services.app.carrequestservice.model.Ad;
import services.app.carrequestservice.model.Invoice;
import services.app.carrequestservice.model.Report;
import services.app.carrequestservice.model.SubmitReport;
import services.app.carrequestservice.repository.AdRepository;
import services.app.carrequestservice.repository.ReportRepository;
import services.app.carrequestservice.service.intf.AdService;
import services.app.carrequestservice.service.intf.ReportService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportSeviceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AdService adService;

    @Override
    public Report findById(Long id) {
        return null;
    }

    @Override
    public List<Report> findAll() {
        return null;
    }

    @Override
    public Report save(Report report) {
        return null;
    }

    @Override
    public void delete(Report report) {

    }

    @Override
    public Integer deleteById(Long id) {
        return null;
    }

    @Override
    public PayDTO payRent(Long requestId) {
        return null;
    }

    @Override
    public void submitReport(SubmitReportDTO submitReport, Long id_agent) {
       Ad ad = adService.findById(submitReport.getAdId());
        float payAmount = 0;
       if(ad.getDistanceLimitFlag().equals("LIMITED")){
           if(ad.getDistanceLimit()<=submitReport.getDistanceTraveled()){
               if(ad.getCdw())
                    payAmount = (submitReport.getDistanceTraveled() -  ad.getDistanceLimit()) * ad.getPricePerKmCDW();
                else
                    payAmount = (submitReport.getDistanceTraveled() -  ad.getDistanceLimit()) * ad.getPricePerKm();
           }
       }
        System.out.println(payAmount);
        Invoice invoice = new Invoice();
        invoice.setAmount(payAmount);
        invoice.setEmail(submitReport.getEmail());

        adService.save(ad);
    }
}
