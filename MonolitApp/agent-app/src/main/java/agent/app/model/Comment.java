package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = DbTableConstants.COMMENT)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.CONTENT, nullable = false)
    private String content;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.CREATIONDATE, nullable = false)
    private DateTime creationDate;

    @Column(name = DbColumnConstants.APPROVED, nullable = false)
    private Boolean approved;

    @ManyToOne(fetch = FetchType.LAZY)
    private PublisherUser publisherUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ad ad;
}