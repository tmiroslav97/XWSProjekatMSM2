package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = DbTableConstants.ENDUSER)
public class EndUser extends PublisherUser {

    @Column(name = DbColumnConstants.ADLIMITNUM, nullable = false)
    private Integer adLimitNum = 3;

    @Column(name = DbColumnConstants.ENABLED, nullable = false)
    private Boolean enabled;

    @Column(name = DbColumnConstants.OBLIGED, nullable = false)
    private Boolean obliged;

    @Column(name = DbColumnConstants.CANCELEDCNT, nullable = false)
    private Integer canceledCnt = 0;

    @OneToMany(mappedBy = "endUser", fetch = FetchType.LAZY)
    private Set<Request> requests = new HashSet<>();

    @Builder(builderMethodName = "endUserBuilder")
    public EndUser(Long id, String email, String password, String firstName,
                   String lastName, DateTime lastPasswordResetDate,
                   List<Authority> authorities, Boolean deleted, Set<Ad> ads,
                   Set<PriceList> priceLists, Set<Comment> comments,
                   Set<Message> inbox, Set<Report> reports, Integer adLimitNum,
                   Boolean enabled, Boolean obliged, Integer canceledCnt,
                   Set<Request> requests) {
        super(id, email, password, firstName, lastName, lastPasswordResetDate, authorities, deleted, ads, priceLists, comments, inbox, reports);
        this.adLimitNum = adLimitNum;
        this.enabled = enabled;
        this.obliged = obliged;
        this.canceledCnt = canceledCnt;
        this.requests = requests;
    }
}