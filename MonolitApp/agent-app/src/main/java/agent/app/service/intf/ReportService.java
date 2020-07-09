package agent.app.service.intf;

import agent.app.dto.carreq.SubmitReportDTO;
import agent.app.model.Report;

import java.util.List;

public interface ReportService {

    Report findById(Long id);

    List<Report> findAll();

    Report save(Report report);

    void delete(Report report);

    Integer deleteById(Long id);

    void submitReport(SubmitReportDTO submitReport, String email_agent);

}
