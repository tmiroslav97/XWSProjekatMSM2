package services.app.authenticationservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import services.app.authenticationservice.common.db.DbColumnConstants;
import services.app.authenticationservice.common.db.DbTableConstants;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.FIRM)
public class Firm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.ADDRESS, nullable = false)
    private String address;

    @Column(name = DbColumnConstants.PMB, unique = true, nullable = false)
    private Long pmb;

    @Column(name = DbColumnConstants.FIRMNAME, nullable = false)
    private String firmName;
}
