package agent.app.controller;

import agent.app.dto.carreq.SubmitReportDTO;
import agent.app.model.PublisherUser;
import agent.app.service.intf.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/rep", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> submitReport(@RequestBody SubmitReportDTO submitReportDTO, Principal principal) {
        System.out.println("REPORT CONTROLERRR");
        reportService.submitReport(submitReportDTO, principal.getName());
        return new ResponseEntity<>("Zahtjev uspjesno kreiran.", HttpStatus.OK);

    }
}
