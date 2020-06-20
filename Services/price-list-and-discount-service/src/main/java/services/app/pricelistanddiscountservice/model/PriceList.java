package services.app.pricelistanddiscountservice.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import services.app.pricelistanddiscountservice.common.db.DbColumnConstants;
import services.app.pricelistanddiscountservice.common.db.DbTableConstants;

import javax.persistence.*;
import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = DbTableConstants.PRICELIST)
public class PriceList {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Temporal(TemporalType.DATE)
   @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime", parameters = {
           @org.hibernate.annotations.Parameter(name = "databaseZone", value = "UTC"),
           @org.hibernate.annotations.Parameter(name = "javaZone", value = "UTC")
   })
   @Column(name = DbColumnConstants.CREATIONDATE, nullable = false)
   private DateTime creationDate;

   @Column(name = DbColumnConstants.PRICEPERDAY, nullable = false)
   private Float pricePerDay;

   @Column(name = DbColumnConstants.PRICEPERKM)
   private Float pricePerKm;

   @Column(name = DbColumnConstants.PRICEPERCWD)
   private Float pricePerKmCDW;

   @Column(name = DbColumnConstants.PUBLISHERUSER, nullable = false)
   private Long publisherUser;
 
}