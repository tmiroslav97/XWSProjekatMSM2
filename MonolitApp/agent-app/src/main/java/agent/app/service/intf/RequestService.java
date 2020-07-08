package agent.app.service.intf;

import agent.app.model.Request;

import java.util.List;

public interface RequestService {
    Request findById(Long id);

    List<Request> findAll();

    Integer deleteById(Long id);

    String findRequestPublisherUserIdentifier(String email);

    List<Request> findAllByPublisherUserAndStatus(String email, String status);

    void delete(Request request);

    Request save(Request request);
}
