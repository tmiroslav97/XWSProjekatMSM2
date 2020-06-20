package services.app.carrequestservice.service.intf;

import services.app.carrequestservice.dto.carreq.SubmitRequestDTO;
import services.app.carrequestservice.model.Request;

import java.util.HashMap;
import java.util.List;

public interface RequestService {

    Request findById(Long id);

    List<Request> findAll();

    List<Request> findAllByEndUserId(Long id);

    List<Request> findAllByEndUserIdAndByStatus(Long id, String status);

    List<Request> findAllByPublisherUserId(Long id);

    List<Request> findAllByPublisherUserEmail(String email);

    List<Request> findAllByPublisherUserEmailAndStatus(String email, String status);

    List<Request> findAllByPublisherUserIdAndByStatus(Long id, String status);

    Integer deleteById(Long id);

    Integer submitRequest(HashMap<Long, SubmitRequestDTO> submitRequestDTOS, Long userId);

    void delete(Request request);

    Request save(Request request);
}
