//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.11 at 12:41:44 AM CEST 
//


package services.app.adservice.model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the services.app.adservice.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: services.app.adservice.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateAdRequest }
     * 
     */
    public CreateAdRequest createCreateAdRequest() {
        return new CreateAdRequest();
    }

    /**
     * Create an instance of {@link AdSync }
     * 
     */
    public AdSync createAdSync() {
        return new AdSync();
    }

    /**
     * Create an instance of {@link CreateAdResponse }
     * 
     */
    public CreateAdResponse createCreateAdResponse() {
        return new CreateAdResponse();
    }

    /**
     * Create an instance of {@link AddCarCalendarTermRequest }
     * 
     */
    public AddCarCalendarTermRequest createAddCarCalendarTermRequest() {
        return new AddCarCalendarTermRequest();
    }

    /**
     * Create an instance of {@link AddCarCalendarTermResponse }
     * 
     */
    public AddCarCalendarTermResponse createAddCarCalendarTermResponse() {
        return new AddCarCalendarTermResponse();
    }

    /**
     * Create an instance of {@link AddCarCalendarOccupationRequest }
     * 
     */
    public AddCarCalendarOccupationRequest createAddCarCalendarOccupationRequest() {
        return new AddCarCalendarOccupationRequest();
    }

    /**
     * Create an instance of {@link AddCarCalendarOccupationResponse }
     * 
     */
    public AddCarCalendarOccupationResponse createAddCarCalendarOccupationResponse() {
        return new AddCarCalendarOccupationResponse();
    }

    /**
     * Create an instance of {@link ReversePricelistRequest }
     * 
     */
    public ReversePricelistRequest createReversePricelistRequest() {
        return new ReversePricelistRequest();
    }

    /**
     * Create an instance of {@link ReversePricelistResponse }
     * 
     */
    public ReversePricelistResponse createReversePricelistResponse() {
        return new ReversePricelistResponse();
    }

    /**
     * Create an instance of {@link CarSync }
     * 
     */
    public CarSync createCarSync() {
        return new CarSync();
    }

    /**
     * Create an instance of {@link CarCalendarTermSync }
     * 
     */
    public CarCalendarTermSync createCarCalendarTermSync() {
        return new CarCalendarTermSync();
    }

}
