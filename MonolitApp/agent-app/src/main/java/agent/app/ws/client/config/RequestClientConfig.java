package agent.app.ws.client.config;

import agent.app.ws.client.RequestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class RequestClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("agent.app.gen");
        return marshaller;
    }
    @Bean
    public RequestClient requestClient(Jaxb2Marshaller marshaller) {
        RequestClient client = new RequestClient();
        client.setDefaultUri("http://localhost:8082/carreq/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
