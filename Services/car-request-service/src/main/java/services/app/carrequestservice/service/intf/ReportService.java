package services.app.carrequestservice.service.intf;

import services.app.carrequestservice.dto.PayDTO;
import services.app.carrequestservice.dto.carreq.SubmitReportDTO;
import services.app.carrequestservice.model.Report;
import services.app.carrequestservice.model.Request;
import services.app.carrequestservice.model.SubmitReport;

import java.util.List;

public interface ReportService {

    Report findById(Long id);

    List<Report> findAll();

    Report save(Report report);

    void delete(Report report);

    Integer deleteById(Long id);

    PayDTO payRent(Long requestId);

    void submitReport(SubmitReportDTO submitReport, Long id_agent);

}
