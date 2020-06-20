package agent.app.model;

import agent.app.common.db.DbTableConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.ADMINISTRATOR)
public class Administrator extends User {

    @Builder(builderMethodName = "administratorBuilder")
    public Administrator(Long id, String email, String password, String firstName,
                         String lastName, DateTime lastPasswordResetDate,
                         List<Authority> authorities) {
        super(id, email, password, firstName, lastName, lastPasswordResetDate, authorities);
    }
}
