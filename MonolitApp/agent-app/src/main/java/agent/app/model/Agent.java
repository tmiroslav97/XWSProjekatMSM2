package agent.app.model;

import agent.app.common.db.DbTableConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.AGENT)
public class Agent extends PublisherUser {

    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    private Set<DiscountList> discountLists = new HashSet<>();

    @Builder(builderMethodName = "agentFirmBuilder")
    public Agent(Long id, String email, String password, String firstName,
                 String lastName, DateTime lastPasswordResetDate,
                 List<Authority> authorities, Boolean deleted, Set<Ad> ads,
                 Set<PriceList> priceLists, Set<Comment> comments,
                 Set<Message> inbox, Set<Report> reports,
                 Set<DiscountList> discountLists) {
        super(id, email, password, firstName, lastName, lastPasswordResetDate, authorities, deleted, ads, priceLists, comments, inbox, reports);
        this.discountLists = discountLists;
    }
}