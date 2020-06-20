package agent.app.service.intf;

import agent.app.dto.carreq.SubmitRequestDTO;
import agent.app.model.Request;

import java.util.List;

public interface RequestService {
    Request findById(Long id);

    List<Request> findAll();

    Integer deleteById(Long id);

    Integer submitRequest(List<SubmitRequestDTO> submitRequestDTOS, String email);

    void delete(Request request);

    Request save(Request request);
}
