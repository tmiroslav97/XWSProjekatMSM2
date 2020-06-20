package services.app.codebookservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import services.app.codebookservice.common.db.DbColumnConstants;
import services.app.codebookservice.common.db.DbTableConstants;
import sun.management.resources.agent;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = DbTableConstants.CARMANUFACTURER)
public class CarManufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, unique = true, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "carManufacturer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<CarModel> carModels = new HashSet<>();
}
