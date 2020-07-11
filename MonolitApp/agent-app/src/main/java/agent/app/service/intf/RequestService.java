package agent.app.service.intf;

import agent.app.dto.carreq.RequestDTO;
import agent.app.model.Request;

import java.util.List;

public interface RequestService {
    Request findById(Long id);

    RequestDTO findRequestDTOById(Long id);

    List<Request> findAll();

    Integer deleteById(Long id);

    String findRequestPublisherUserIdentifier(String email);

    Boolean rejectOtherRequests(Request request);

    List<RequestDTO> findAllByPublisherUserAndStatus(String email, String status);

    String acceptRequest(String email, Long id, String action);

    void requestSync(String msg);

    void delete(Request request);

    Request save(Request request);
}
