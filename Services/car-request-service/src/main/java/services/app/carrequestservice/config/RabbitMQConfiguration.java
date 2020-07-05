package services.app.carrequestservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String AGENT_ID_BY_EMAIL_ID_QUEUE_NAME = "agent_id_by_email_id";
    public static final String AD_ACCEPT_REQ_QUEUE_NAME = "ad_accept_req";

    @Bean
    public Queue adAcceptReq() {
        return new Queue(AD_ACCEPT_REQ_QUEUE_NAME, false);
    }

    @Bean
    public Queue agentIdByEmailId() {
        return new Queue(AGENT_ID_BY_EMAIL_ID_QUEUE_NAME, false);
    }

}
