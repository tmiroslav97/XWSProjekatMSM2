//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.11 at 01:37:58 AM CEST 
//


package org.w3._2001.xmlschema;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Boolean>
{


    public Boolean unmarshal(String value) {
        return (agent.app.converter.TypeConverter.parseBoolean(value));
    }

    public String marshal(Boolean value) {
        return (agent.app.converter.TypeConverter.printBoolean(value));
    }

}
