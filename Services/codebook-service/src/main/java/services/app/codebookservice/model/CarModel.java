package services.app.codebookservice.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import services.app.codebookservice.common.db.DbColumnConstants;
import services.app.codebookservice.common.db.DbTableConstants;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = DbTableConstants.CARMODEL)
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.NAME, unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private CarManufacturer carManufacturer;

    @JsonProperty("carManufacturer")
    public String getTheCarManufacturer() {
        return carManufacturer.getName();
    }

    @JsonProperty("carManufacturer")
    public void setTheCarManufacturer(Long id) {
        carManufacturer = CarManufacturer.builder()
                .id(id)
                .build();
    }
}