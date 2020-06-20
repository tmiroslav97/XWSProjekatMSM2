package services.app.adsearchservice.model;


import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import services.app.adsearchservice.common.db.DbColumnConstants;
import services.app.adsearchservice.common.db.DbTableConstants;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = DbTableConstants.CARCALENDARTERM)
public class CarCalendarTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Ad ad;
}