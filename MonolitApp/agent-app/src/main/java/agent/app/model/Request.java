package agent.app.model;


import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import agent.app.model.enumeration.RequestStatusEnum;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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
@Table(name = DbTableConstants.REQUEST)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = DbColumnConstants.STATUS, nullable = false)
    private RequestStatusEnum status;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.SUBMITDATE, nullable = false)
    private DateTime submitDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private EndUser endUser;

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

    @Column(name = DbColumnConstants.BUNDLE, nullable = false)
    private Boolean bundle;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Ad> ads = new HashSet<>();

    @OneToMany(mappedBy = "request",fetch = FetchType.LAZY)
    private Set<Message> messages = new HashSet<>();

}