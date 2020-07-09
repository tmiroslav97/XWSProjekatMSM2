package services.app.carrequestservice.model;

import lombok.*;
import org.w3._2001.xmlschema.Adapter5;
import services.app.carrequestservice.common.db.DbColumnConstants;
import services.app.carrequestservice.common.db.DbTableConstants;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = DbTableConstants.INVOICE)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = DbColumnConstants.AMOUNT, nullable = false)
    protected Float amount;

    @Column(name = DbColumnConstants.EMAIL, nullable = false)
    protected String email;



}
