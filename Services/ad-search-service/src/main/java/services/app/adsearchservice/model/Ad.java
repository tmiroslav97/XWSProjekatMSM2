package services.app.adsearchservice.model;


import lombok.*;
import org.hibernate.annotations.Type;

import org.joda.time.DateTime;
import services.app.adsearchservice.common.db.DbColumnConstants;
import services.app.adsearchservice.common.db.DbTableConstants;
import services.app.adsearchservice.model.enumeration.DistanceLimitEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = DbTableConstants.AD)
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, nullable = false)
    private String name;

    @Column(name = DbColumnConstants.LOCATION, nullable = false)
    private String location;

    @Column(name = DbColumnConstants.COVERPHOTO, nullable = false)
    private String coverPhoto;

    @Enumerated(EnumType.STRING)
    @Column(name = DbColumnConstants.DISTANCELIMITFLAG, nullable = false)
    private DistanceLimitEnum distanceLimitFlag;

    @Column(name = DbColumnConstants.DISTANCELIMIT)
    private Float distanceLimit;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.PUBLISHDATE, nullable = false)
    private DateTime publishedDate;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY )
    private Set<Image> images = new HashSet<>();

    @Column(name = DbColumnConstants.RATINGNUM, nullable = false)
    private Long ratingNum = 0L;

    @Column(name = DbColumnConstants.RATINGCOUNT, nullable = false)
    private Long ratingCnt = 0L;

    @Column(name = DbColumnConstants.DELETED, nullable = false)
    private Boolean deleted;

    @Column(name = DbColumnConstants.ENABLED, nullable = false)
    private Boolean enabled;

    @Column(name = DbColumnConstants.RENTCNT, nullable = false)
    private Long rentCnt = 0L;

    @Column(name = DbColumnConstants.PRICEPERDAY, nullable = false)
    private Float pricePerDay;

    @Column(name = DbColumnConstants.PUBLISHERUSER, nullable = false)
    private Long publisherUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = DbTableConstants.ADCAR,
            joinColumns = @JoinColumn(name = "ad_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private Car car;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY)
    private Set<CarCalendarTerm> carCalendarTerms = new HashSet<>();


}