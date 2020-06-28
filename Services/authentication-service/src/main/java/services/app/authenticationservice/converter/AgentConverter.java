package services.app.authenticationservice.converter;

import services.app.authenticationservice.dto.agent.AgentDTO;
import services.app.authenticationservice.model.Agent;

public class AgentConverter extends AbstractConverter {

    public static AgentDTO fromAgentToAgentDTO(Agent agent) {
        return AgentDTO.builder()
                .id(agent.getId())
                .email(agent.getEmail())
                .firstName(agent.getFirstName())
                .lastName(agent.getLastName())
                .local(agent.getLocal())
                .deleted(agent.getDeleted())
                .build();
    }
}
