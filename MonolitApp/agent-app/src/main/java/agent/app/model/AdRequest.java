package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import agent.app.converter.DateAPI;
import agent.app.model.enumeration.DistanceLimitEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = DbTableConstants.ADREQUEST)
public class AdRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.ADNAME, nullable = false)
    private String adName;

    @Column(name = DbColumnConstants.MAINID, nullable = false)
    private Long mainId;

    @Column(name = DbColumnConstants.ADID, nullable = false)
    private Long adId;

    @Column(name = DbColumnConstants.REVIEW)
    private Integer review;

    @Column(name = DbColumnConstants.DISCOUNT)
    private Float discount;

    @Column(name = DbColumnConstants.MILEAGE, nullable = false)
    private Float mileage;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.STARTDATE, nullable = false)
    private DateTime startDate;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.ENDDATE, nullable = false)
    private DateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = DbColumnConstants.DISTANCELIMITFLAG, nullable = false)
    private DistanceLimitEnum distanceLimitFlag;

    @Column(name = DbColumnConstants.DISTANCELIMIT)
    private Float distanceLimit;

    @Column(name = DbColumnConstants.CDW, nullable = false)
    private Boolean cdw;

    @Column(name = DbColumnConstants.PRICEPERDAY, nullable = false)
    private Float pricePerDay;

    @Column(name = DbColumnConstants.PRICEPERKM)
    private Float pricePerKm;

    @Column(name = DbColumnConstants.PRICEPERCWD)
    private Float pricePerKmCDW;

    @Column(name = DbColumnConstants.TOKEN)
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = DbTableConstants.ADREPORT,
            joinColumns = @JoinColumn(name = "ad_request_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id"))
    private Report report;

    @JsonProperty("startDate")
    public String getTheStartDate() {
        return DateAPI.DateTimeToStringDateTime(startDate);
    }

    @JsonProperty("endDate")
    public String getTheEndDate() {
        return DateAPI.DateTimeToStringDateTime(endDate);
    }
}
