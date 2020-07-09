package agent.app.dto.carreq;

import agent.app.dto.adrequest.AdRequestDTO;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    protected Long id;
    protected String status;
    protected String submitDate;
    protected Long endUserId;
    protected String endUserFirstName;
    protected String endUserLastName;
    protected String endUserEmail;
    protected Boolean bundle;
    protected Long publisherUserId;
    protected String publisherUserFirstName;
    protected String publisherUserLastName;
    protected String publisherUserEmail;
    protected List<AdRequestDTO> ads;
}
