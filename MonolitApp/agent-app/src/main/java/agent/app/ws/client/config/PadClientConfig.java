package agent.app.ws.client.config;

import agent.app.config.AppConfig;
import agent.app.ws.client.PadClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class PadClientConfig {

    @Autowired
    private AppConfig appConfig;

    @Bean
    public Jaxb2Marshaller marshallerPad() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("services.app.pricelistanddiscountservice.model");
        return marshaller;
    }

    @Bean
    public PadClient padClient(@Qualifier("marshallerPad") Jaxb2Marshaller marshallerPad) {
        PadClient client = new PadClient();
        client.setDefaultUri("http://" + appConfig.getZuul() + ":8088/pad/ws");
        client.setMarshaller(marshallerPad);
        client.setUnmarshaller(marshallerPad);
        return client;
    }
}
