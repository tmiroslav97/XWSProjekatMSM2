package agent.app.controller;

import agent.app.dto.carreq.ListSubmitRequestDTO;
import agent.app.service.intf.RequestService;
import agent.app.ws.client.RequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/req")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    private RequestClient requestClient;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> submitRequest(@RequestBody ListSubmitRequestDTO listSubmitRequestDTO, Principal principal) {
        String email = principal.getName();
        Integer flag = requestService.submitRequest(listSubmitRequestDTO.getSubmitRequestDTOS(), email);
        if (flag == 1) {
            return new ResponseEntity<>("Zahtjev uspjesno kreiran.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/publisher-user", method = RequestMethod.GET)
    public ResponseEntity<?> getPublisherUserRequests(@RequestHeader(value = "status", required = false) String status, Principal principal) {
        String email = principal.getName();
        return new ResponseEntity<>(requestClient.getPublisherRequestsResponse(email, status), HttpStatus.OK);
    }
}
