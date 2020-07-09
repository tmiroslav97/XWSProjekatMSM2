package services.app.messageservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String USER_FL_NAME_QUEUE_NAME = "user_fl_name";
    public static final String AGENT_SYNC_QUEUE_NAME = "agent.sync";

    @Bean
    public Queue userFLNameQueue() {
        return new Queue(USER_FL_NAME_QUEUE_NAME, false);
    }

}
