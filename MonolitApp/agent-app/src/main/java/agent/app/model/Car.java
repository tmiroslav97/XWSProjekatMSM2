package agent.app.model;


import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
@Setter
@Entity
@Table(name = DbTableConstants.CAR)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.YEAR, nullable = false)
    private DateTime year;

    @Column(name = DbColumnConstants.CARMANUFACTURER, nullable = false)
    private String carManufacturer;

    @Column(name = DbColumnConstants.CARMODEL, nullable = false)
    private String carModel;

    @Column(name = DbColumnConstants.GEARBOXTYPE, nullable = false)
    private String gearboxType;

    @Column(name = DbColumnConstants.FUELTYPE, nullable =  false)
    private String fuelType;

    @Column(name = DbColumnConstants.CARTYPE, nullable = false)
    private String carType;

    @Column(name = DbColumnConstants.MILEAGE, nullable = false)
    private Float mileage;

    @Column(name = DbColumnConstants.CHILDRENSEATNUM, nullable = false)
    private Integer childrenSeatNum;

    @Column(name = DbColumnConstants.CDW, nullable = false)
    private Boolean cdw;

    @Column(name = DbColumnConstants.ANDROIDFLAG)
    private Boolean androidFlag;

    @Column(name = DbColumnConstants.TOKEN)
    private String token;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Ad ad;
}