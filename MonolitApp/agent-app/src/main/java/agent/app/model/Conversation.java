package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.w3._2001.xmlschema.Adapter5;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = DbTableConstants.CONVERSATION)
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.MAINID, nullable = false, unique = true)
    private Long mainId;

    @Column(name = DbColumnConstants.CONVNAME, nullable = false)
    private String convName;

    @Column(name = DbColumnConstants.REQUESTID, nullable = false, unique = true)
    private Long requestId;

    @Column(name = DbColumnConstants.PARTICIPANTPUBLISHERUSERID, nullable = false)
    private Long participantPublisherUserId;

    @Column(name = DbColumnConstants.PARTICIPANTENDUSERID, nullable = false)
    private Long participantEndUserId;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = DbTableConstants.CONVMSG,
            joinColumns = @JoinColumn(name = "conv_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "msg_id", referencedColumnName = "id"))
    @OrderBy("sendDate DESC")
    private List<Message> message;
}
