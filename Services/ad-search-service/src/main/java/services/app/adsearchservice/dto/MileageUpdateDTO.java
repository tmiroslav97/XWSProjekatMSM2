package services.app.adsearchservice.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MileageUpdateDTO {
    private Long adId;
    private Float mileage;
}
