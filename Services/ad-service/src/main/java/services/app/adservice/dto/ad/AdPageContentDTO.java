package services.app.adservice.dto.ad;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AdPageContentDTO {
    private Integer totalPageCnt;
    List<AdPageDTO> ads;
}
