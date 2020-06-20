package agent.app.dto.ad;

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

//    @Override
//    public String toString() {
//        return "AdPageContentDTO{" +
//                "ads=" + ads +
//                '}';
//    }
}
