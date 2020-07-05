package services.app.authenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import services.app.authenticationservice.dto.agent.AgentRegDTO;
import services.app.authenticationservice.service.intf.AgentService;

@RestController
@RequestMapping(value = "/agent", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(value = "nextPage", required = false) Integer nextPage, @RequestParam(value = "size", required = false) Integer size) {
        if (nextPage != null) {
            return new ResponseEntity<>(agentService.findAll(nextPage, size), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(agentService.findAll(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerAgent(@RequestBody AgentRegDTO agentRegDTO) {
        Integer flag = agentService.registerAgent(agentRegDTO);
        if (flag == 1) {
            return new ResponseEntity<>("Agent uspjesno registrovan.", HttpStatus.CREATED);
        } else if (flag == 2) {
            return new ResponseEntity<>("Greska pri registraciji agenta.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delOrRevert(@RequestHeader("action") String action, @PathVariable("id") Long id, @RequestBody Boolean status) {
        if (action.equals("log-del")) {
            Integer flag = agentService.logicDeleteOrRevertById(id, status);
            if (flag == 1) {
                return new ResponseEntity<>("Agent uspjesno logicki obrisan.", HttpStatus.OK);
            } else if (flag == 2) {
                return new ResponseEntity<>("Agent uspjesno vracen.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Los zahtjev", HttpStatus.BAD_REQUEST);
        }
    }
}
