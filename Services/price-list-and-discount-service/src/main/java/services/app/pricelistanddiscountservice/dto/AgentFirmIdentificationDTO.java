package services.app.pricelistanddiscountservice.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AgentFirmIdentificationDTO {
    private String email;
    private String identifier;
}
