package services.app.pricelistanddiscountservice.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import services.app.pricelistanddiscountservice.common.db.DbColumnConstants;
import services.app.pricelistanddiscountservice.common.db.DbTableConstants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = DbTableConstants.DISCOUNTLIST)
public class DiscountList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.DAYNUM, nullable = false)
    private Integer dayNum;

    @Column(name = DbColumnConstants.DISCOUNT, nullable = false)
    private Float discount;

    @Column(name = DbColumnConstants.AGENT, nullable = false)
    private Long agent;


}