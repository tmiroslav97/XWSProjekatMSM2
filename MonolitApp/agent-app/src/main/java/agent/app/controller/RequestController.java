package agent.app.controller;

import agent.app.dto.carreq.ReqAcceptDTO;
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

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(value = "/publisher-user", method = RequestMethod.GET)
    public ResponseEntity<?> getPublisherUserRequests(@RequestHeader(value = "status", required = false) String status, Principal principal) {
        String email = principal.getName();
        return new ResponseEntity<>(requestService.findAllByPublisherUserAndStatus(email, status), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_AGENT')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> acceptRequest(@RequestBody ReqAcceptDTO reqAcceptDTO, Principal principal) {
        String email = principal.getName();
        String identifier = requestService.findRequestPublisherUserIdentifier(email);
        if (reqAcceptDTO.getAction() != null && (reqAcceptDTO.getAction().equals("accept") || reqAcceptDTO.getAction().equals("reject"))) {
                String response = requestClient.acceptRequest(email, identifier, reqAcceptDTO.getId(), reqAcceptDTO.getAction());
                if (response.equals("Uspjesno odbijen zahtjev") || response.equals("Uspjesno prihvacen zahtjev")) {
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
        } else {
            return new ResponseEntity<>("Nepoznata akcija", HttpStatus.BAD_REQUEST);

        }
    }
}
