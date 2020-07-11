package services.app.carrequestservice.controller;

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
import services.app.carrequestservice.dto.carreq.SubmitReportDTO;
import services.app.carrequestservice.model.CustomPrincipal;
import services.app.carrequestservice.service.intf.ReportService;

@RestController
@RequestMapping(value = "/rep", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> submitReport(@RequestBody SubmitReportDTO submitReportDTO) {
        System.out.println("REPORT CONTROLERRR");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        reportService.submitReport(submitReportDTO, Long.valueOf(cp.getUserId()));
        return new ResponseEntity<>("Izvjestaj uspjesno dodat.", HttpStatus.OK);

    }
}
