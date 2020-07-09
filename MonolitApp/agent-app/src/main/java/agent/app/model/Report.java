package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = DbTableConstants.REPORT)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = DbColumnConstants.MAINID)
    private Long mainId;

    @Column(name = DbColumnConstants.DESCRIPTION)
    private String description;

    @Column(name = DbColumnConstants.DISTANCETRAVELED, nullable = false)
    private Float distanceTraveled;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = DbTableConstants.REPORTINVOICE,
            joinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id", referencedColumnName = "id"))
    protected Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    private PublisherUser publisherUser;


}