package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = DbTableConstants.PUBLISHERUSER)
public class PublisherUser extends User {

    @Column(name = DbColumnConstants.DELETED, nullable = false)
    private Boolean deleted;

    @OneToMany(mappedBy = "publisherUser", fetch = FetchType.LAZY)
    private Set<Ad> ads = new HashSet<>();

    @OneToMany(mappedBy = "publisherUser", fetch = FetchType.LAZY)
    private Set<PriceList> priceLists = new HashSet<>();

    @OneToMany(mappedBy = "publisherUser", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private Set<Message> inbox = new HashSet<>();

    @OneToMany(mappedBy = "publisherUser", fetch = FetchType.LAZY)
    private Set<Report> reports = new HashSet<>();

    @Builder(builderMethodName = "publisherUserBuilder")
    public PublisherUser(Long id, String email, String password, String firstName,
                         String lastName, DateTime lastPasswordResetDate,
                         List<Authority> authorities, Boolean deleted, Set<Ad> ads,
                         Set<PriceList> priceLists, Set<Comment> comments,
                         Set<Message> inbox, Set<Report> reports) {
        super(id, email, password, firstName, lastName, lastPasswordResetDate, authorities);
        this.deleted = deleted;
        this.ads = ads;
        this.priceLists = priceLists;
        this.comments = comments;
        this.inbox = inbox;
        this.reports = reports;
    }

}