package agent.app.dto.ad;

import agent.app.model.enumeration.DistanceLimitEnum;

import java.util.List;

public class AdEditDTO {
    private String name;
    private String location;
    private String coverPhoto;
    private DistanceLimitEnum distanceLimitFlag;
    private Float distanceLimit;
}
