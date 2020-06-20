package agent.app.model;

import agent.app.common.db.DbColumnConstants;
import agent.app.common.db.DbTableConstants;
import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@ToString
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

   @ManyToOne(fetch = FetchType.LAZY)
   private PublisherUser publisherUser;

   @OneToMany(mappedBy = "priceList", fetch = FetchType.LAZY)
   private Set<Ad> ads = new HashSet<>();
   
 
}