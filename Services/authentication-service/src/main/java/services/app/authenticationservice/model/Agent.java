package services.app.authenticationservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
import services.app.authenticationservice.common.db.DbTableConstants;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.AGENT)
public class Agent extends User {

    @Builder(builderMethodName = "agentFirmBuilder")
    public Agent(Long id, String email, String password, String firstName,
                 String lastName, DateTime lastPasswordResetDate,
                 List<Authority> authorities, Boolean deleted) {
        super(id, email, password, firstName, lastName, lastPasswordResetDate, deleted, authorities);
    }
}