package agent.app.ws.client.config;

import agent.app.config.AppConfig;
import agent.app.ws.client.MsgClient;
import agent.app.ws.client.RequestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MsgClientConfig {

    @Autowired
    private AppConfig appConfig;

    @Bean
    public Jaxb2Marshaller marshallerMsg() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("services.app.messageservice.model");
        return marshaller;
    }

    @Bean
    public MsgClient msgClient(@Qualifier("marshallerMsg") Jaxb2Marshaller marshallerMsg) {
        MsgClient client = new MsgClient();
        client.setDefaultUri("http://" + appConfig.getZuul() + ":8082/msg/ws");
        client.setMarshaller(marshallerMsg);
        client.setUnmarshaller(marshallerMsg);
        return client;
    }
}
