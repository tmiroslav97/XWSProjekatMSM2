//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.09 at 10:25:29 PM CEST 
//


package org.w3._2001.xmlschema;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter6
    extends XmlAdapter<String, Integer>
{


    public Integer unmarshal(String value) {
        return (services.app.messageservice.converter.TypeConverter.parseInteger(value));
    }

    public String marshal(Integer value) {
        return (services.app.messageservice.converter.TypeConverter.printInteger(value));
    }

}
