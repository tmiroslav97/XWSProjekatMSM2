package services.app.authenticationservice.dto;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EndUserPageDTO {
    private List<EndUserDTO> endUsers;
    private Integer totalPageCnt;
}
