package services.app.carrequestservice.controller;

import org.springframework.web.bind.annotation.RestController;
import services.app.carrequestservice.service.intf.ReportService;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
}
