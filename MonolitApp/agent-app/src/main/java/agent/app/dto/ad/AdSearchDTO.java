package agent.app.dto.ad;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class AdSearchDTO {

    List<AdOrdinarySearchDTO> adOrdinarySearchDTO;
    List<AdAdvancedSearchDTO> adAdvancedSearchDTO;
    private Integer totalPages;

}
