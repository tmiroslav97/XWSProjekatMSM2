package services.app.carrequestservice.service.intf;

import services.app.carrequestservice.dto.carreq.SubmitRequestDTO;
import services.app.carrequestservice.dto.discountlist.DiscountInfoDTO;
import services.app.carrequestservice.model.Ad;
import services.app.carrequestservice.model.Request;

import java.util.HashMap;
import java.util.List;

public interface RequestService {

    Request findById(Long id);

    List<Request> findAll();

    List<Request> findAllByEndUserId(Long id);

    List<Request> findAllByEndUserIdAndByStatus(Long id, String status);

    List<Request> findAllByPublisherUserId(Long id);

    Long authAgent(String email, String identifier);

    Integer quitRequest(Long id);

    void autoRejectRequests();

    void deleteAllWithSameAdId(List<Ad> ads);

    String acceptRequest(Long requestId, String action);

    Boolean rejectOtherRequests(Request request);

    List<Request> findAllByPublisherUserIdAndByStatus(Long id, String status);

    Integer deleteById(Long id);

    Float findClossestDiscount(List<DiscountInfoDTO> discountInfoDTOS, Integer dayOfRent);

    Integer submitRequest(HashMap<Long, SubmitRequestDTO> submitRequestDTOS, Long userId);

    void delete(Request request);

    Request save(Request request);
}
