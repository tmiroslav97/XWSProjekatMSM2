package services.app.authenticationservice.model;

import lombok.*;
import services.app.authenticationservice.common.db.DbColumnConstants;
import services.app.authenticationservice.common.db.DbTableConstants;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = DbColumnConstants.EMAIL, nullable = false)
    private String email;

    @Column(name = DbColumnConstants.IDENTIFIER, nullable = false)
    private String identifier;

    @Column(name = DbColumnConstants.DELETED, nullable = false)
    private Boolean deleted=false;
}
