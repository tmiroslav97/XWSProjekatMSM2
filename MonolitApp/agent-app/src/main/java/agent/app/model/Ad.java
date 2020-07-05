package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import agent.app.model.enumeration.DistanceLimitEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = DbColumnConstants.MAINID, unique = true)
    private Long mainId;

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

    @OneToOne(fetch = FetchType.LAZY)
    private Report report;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = DbTableConstants.ADCAR,
            joinColumns = @JoinColumn(name = "ad_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private Car car;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY)
    private Set<CarCalendarTerm> carCalendarTerms = new HashSet<>();

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY )
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY )
    private Set<Image> images = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private PublisherUser publisherUser;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private PriceList priceList;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<DiscountList> discountLists = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Request> requests = new HashSet<>();

}