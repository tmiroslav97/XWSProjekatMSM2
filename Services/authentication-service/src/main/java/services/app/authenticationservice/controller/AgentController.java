package services.app.authenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.app.authenticationservice.dto.agent.AgentRegDTO;
import services.app.authenticationservice.service.intf.AgentService;

@RestController
@RequestMapping(value = "/agent", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerAgent(@RequestBody AgentRegDTO agentRegDTO) {
        Integer flag = agentService.registerAgent(agentRegDTO);
        if (flag == 1) {
            return new ResponseEntity<>("Agent uspjesno registrovan.", HttpStatus.CREATED);
        } else if(flag==2){
            return new ResponseEntity<>("Greska pri registraciji agenta.", HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>("Desila se nepoznata greska.", HttpStatus.BAD_REQUEST);
        }
    }
}
