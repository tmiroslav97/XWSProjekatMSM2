package agent.app.controller;

import agent.app.dto.carreq.ListSubmitRequestDTO;
import agent.app.service.intf.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/req")
public class RequestController {

    private final RequestService requestService;

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
}
