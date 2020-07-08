package agent.app.model;


import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import agent.app.model.enumeration.RequestStatusEnum;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = DbColumnConstants.MAINID)
    private Long mainId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;

    @Column(name = DbColumnConstants.BUNDLE, nullable = false)
    private Boolean bundle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DbTableConstants.REQUESTAD,
            joinColumns = @JoinColumn(name = "adRequest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "request_id", referencedColumnName = "id"))
    protected List<AdRequest> ads;

}