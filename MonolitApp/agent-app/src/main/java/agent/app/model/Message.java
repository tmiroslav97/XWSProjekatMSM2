package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import agent.app.converter.DateAPI;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.w3._2001.xmlschema.Adapter1;
import org.w3._2001.xmlschema.Adapter4;
import org.w3._2001.xmlschema.Adapter5;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = DbTableConstants.MESSAGE)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
            @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
            @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
    })
    @Column(name = DbColumnConstants.SENDDATE, nullable = false)
    protected DateTime sendDate;

    @Column(name = DbColumnConstants.CONTENT, nullable = false)
    protected String content;

    @Column(name = DbColumnConstants.RECIEVERSEEN, nullable = false)
    protected Boolean recieverSeen;

    @Column(name = DbColumnConstants.CONVERSATIONID, nullable = false)
    protected Long conversationId;

    @ManyToOne(fetch = FetchType.LAZY)
    private PublisherUser senderUser;

    @JsonProperty("sendDate")
    public String getTheSendDate() {
        return DateAPI.DateTimeToStringDateTime(sendDate);
    }

    @JsonProperty("sendDate")
    public void getTheSendDate(String sendDate) {
        this.sendDate = DateAPI.DateTimeStringToDateTime(sendDate);
    }


}