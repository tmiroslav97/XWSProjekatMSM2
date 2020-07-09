package agent.app.service.intf;

import agent.app.model.Agent;

public interface AgentService {
    Agent findByEmail(String email);

    Agent save(Agent agent);
}
