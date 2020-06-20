package services.app.authenticationservice.model;

import lombok.*;
import org.joda.time.DateTime;
import services.app.authenticationservice.common.db.DbColumnConstants;
import services.app.authenticationservice.common.db.DbTableConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = DbTableConstants.ENDUSER)
public class EndUser extends User {

    @Column(name = DbColumnConstants.ADLIMITNUM, nullable = false)
    private Integer adLimitNum = 3;

    @Column(name = DbColumnConstants.ENABLED, nullable = false)
    private Boolean enabled;

    @Column(name = DbColumnConstants.OBLIGED, nullable = false)
    private Boolean obliged;

    @Column(name = DbColumnConstants.CANCELEDCNT, nullable = false)
    private Integer canceledCnt = 0;

    @Builder(builderMethodName = "endUserBuilder")
    public EndUser(Long id, String email, String password, String firstName,
                   String lastName, DateTime lastPasswordResetDate,
                   List<Authority> authorities, Boolean deleted, Integer adLimitNum,
                   Boolean enabled, Boolean obliged, Integer canceledCnt) {
        super(id, email, password, firstName, lastName, lastPasswordResetDate, deleted, authorities);
        this.adLimitNum = adLimitNum;
        this.enabled = enabled;
        this.obliged = obliged;
        this.canceledCnt = canceledCnt;
    }
}