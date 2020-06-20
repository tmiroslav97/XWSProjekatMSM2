package services.app.messageservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import services.app.messageservice.common.db.DbColumnConstants;
import services.app.messageservice.common.db.DbTableConstants;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = DbTableConstants.MESSAGE)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.SENDDATE, nullable = false)
    private DateTime sendDate;

    @Column(name = DbColumnConstants.CONTENT, nullable = false)
    private String content;

    @Column(name = DbColumnConstants.REQUEST, nullable = false)
    private Long requestId;

    @Column(name = DbColumnConstants.OWNER, nullable = false)
    private Long ownerId;
}