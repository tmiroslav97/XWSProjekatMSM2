package services.app.authenticationservice.service.intf;

import services.app.authenticationservice.dto.agent.AgentPageDTO;
import services.app.authenticationservice.dto.agent.AgentRegDTO;
import services.app.authenticationservice.model.Agent;

import java.util.List;


public interface AgentService {
    Agent findById(Long id);

    AgentPageDTO findAll(Integer page, Integer size);

    List<Agent> findAll();

    Integer registerAgent(AgentRegDTO agentRegDTO);

    Integer editAgent();

    Integer logicDeleteOrRevertById(Long id, Boolean status);

    Integer deleteById(Long id);

    void delete(Agent agent);

    Agent save(Agent agent);

    Integer verifyAgent(String msg);

    Long authAgent(String msg);
}
