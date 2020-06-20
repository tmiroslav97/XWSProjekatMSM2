package services.app.adsearchservice.model;


import lombok.*;
import services.app.adsearchservice.common.db.DbColumnConstants;
import services.app.adsearchservice.common.db.DbTableConstants;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = DbTableConstants.IMAGE)
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ad ad;
}
