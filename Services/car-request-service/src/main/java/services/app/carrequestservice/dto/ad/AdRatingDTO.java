package services.app.carrequestservice.dto.ad;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdRatingDTO {
    private Long rating;
    private Long adId;
    private Long mainId;
    private Long requestId;
}
