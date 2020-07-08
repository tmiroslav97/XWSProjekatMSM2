package agent.app.service.impl;

import agent.app.model.Agent;
import agent.app.repository.AgentRepository;
import agent.app.service.intf.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl  implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent findByEmail(String email) {
        return agentRepository.findByEmail(email);
    }

    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }
}
