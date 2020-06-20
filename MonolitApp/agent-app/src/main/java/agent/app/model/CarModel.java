package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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