package agent.app.service.intf;


import agent.app.model.AdRequest;

import java.util.List;

public interface AdRequestService {

    AdRequest findById(Long id);

    List<AdRequest> findAll();

    AdRequest save(AdRequest adRequest);

    void delete(AdRequest adRequest);
}
