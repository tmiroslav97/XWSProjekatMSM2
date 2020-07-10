package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.AGENT)
public class Agent extends PublisherUser {

    @Column(name = DbColumnConstants.IDENTIFIER, unique = true)
    private String identifier;

    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    private Set<DiscountList> discountLists = new HashSet<>();


    @Builder(builderMethodName = "agentFirmBuilder")
    public Agent(Long id, String email, String password, String firstName,
                 String lastName, Boolean local, DateTime lastPasswordResetDate,
                 List<Authority> authorities, Boolean deleted, Set<Ad> ads,
                 Set<PriceList> priceLists, Set<Comment> comments,
                 Set<Report> reports, Set<Request> publisherUserRequests, String identifier,
                 Set<DiscountList> discountLists) {
        super(id, email, password, firstName, lastName, local, lastPasswordResetDate, authorities, deleted, ads, priceLists, comments, reports, publisherUserRequests);
        this.discountLists = discountLists;
        this.identifier = identifier;
    }
}