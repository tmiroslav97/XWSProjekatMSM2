package services.app.adservice.dto.ad;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdRatingDTO {
    private Long rating;
    private Long adId;
}
