package agent.app.ws.client.config;

import agent.app.config.AppConfig;
import agent.app.ws.client.RequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class RequestClientConfig {

    @Autowired
    private AppConfig appConfig;

    @Bean
    public Jaxb2Marshaller marshallerRequest() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("services.app.carrequestservice.model");
        return marshaller;
    }

    @Bean
    public RequestClient requestClient(@Qualifier("marshallerRequest") Jaxb2Marshaller marshallerRequest) {
        RequestClient client = new RequestClient();
        client.setDefaultUri("http://" + appConfig.getZuul() + ":8082/carreq/ws");
        client.setMarshaller(marshallerRequest);
        client.setUnmarshaller(marshallerRequest);
        return client;
    }
}
