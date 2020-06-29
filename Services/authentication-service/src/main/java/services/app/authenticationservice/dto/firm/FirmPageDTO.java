package services.app.authenticationservice.dto.firm;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FirmPageDTO {
    private List<FirmDTO> firms;
    private Integer totalPageCnt;
}
