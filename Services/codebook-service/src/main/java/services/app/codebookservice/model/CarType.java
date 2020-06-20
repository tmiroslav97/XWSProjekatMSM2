package services.app.codebookservice.model;

import lombok.*;
import services.app.codebookservice.common.db.DbColumnConstants;
import services.app.codebookservice.common.db.DbTableConstants;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = DbTableConstants.CARTYPE)
public class CarType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, unique = true, nullable = false)
    private String name;
}