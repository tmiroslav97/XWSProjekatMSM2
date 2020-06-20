package services.app.adservice.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import services.app.adservice.common.db.DbColumnConstants;
import services.app.adservice.common.db.DbTableConstants;

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

    @Column(name = DbColumnConstants.PUBLISHERUSER, nullable = false)
    private Long publisherUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ad ad;
}