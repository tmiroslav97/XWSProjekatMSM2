package services.app.adservice.model;

import lombok.*;
import services.app.adservice.common.db.DbTableConstants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = DbTableConstants.DISCOUNTLIST)
public class DiscountList {

    @Id
    private Long id;

    @ManyToMany(mappedBy = "discountLists", fetch = FetchType.LAZY)
    private Set<Ad> ads = new HashSet<>();
}
