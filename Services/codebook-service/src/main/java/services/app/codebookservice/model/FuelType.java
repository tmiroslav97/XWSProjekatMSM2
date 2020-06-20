package services.app.codebookservice.model;


import lombok.*;
import services.app.codebookservice.common.db.DbColumnConstants;
import services.app.codebookservice.common.db.DbTableConstants;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = DbTableConstants.FUELTYPE)
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, unique = true, nullable = false)
    private String name;

}