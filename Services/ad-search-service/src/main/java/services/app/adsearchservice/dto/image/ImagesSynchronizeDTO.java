package services.app.adsearchservice.dto.image;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ImagesSynchronizeDTO {
    private Long id;
    private String name;
    private Long adId;
}
