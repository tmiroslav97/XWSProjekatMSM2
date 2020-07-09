package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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

    @Column(name = DbColumnConstants.MAINID, unique = true)
    private Long mainId;

    @Column(name = DbColumnConstants.DISCOUNT, nullable = false)
    private Float discount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;

    @ManyToMany(mappedBy = "discountLists", fetch = FetchType.LAZY)
    private Set<Ad> ads = new HashSet<>();

}