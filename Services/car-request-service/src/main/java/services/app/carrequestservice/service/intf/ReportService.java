package services.app.carrequestservice.service.intf;

import services.app.carrequestservice.dto.PayDTO;
import services.app.carrequestservice.model.Report;
import services.app.carrequestservice.model.Request;

import java.util.List;

public interface ReportService {

    Report findById(Long id);
    List<Report> findAll();
    Report save(Report report);
    void delete(Report report);
    Integer deleteById(Long id);
    PayDTO payRent(Long requestId);

}
