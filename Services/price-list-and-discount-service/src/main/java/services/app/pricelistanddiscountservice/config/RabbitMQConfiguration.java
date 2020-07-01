package services.app.pricelistanddiscountservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String PL_SYNC_QUEUE_NAME = "pl_sync_rpc";
    public static final String DL_SYNC_QUEUE_NAME = "dl_sync_rpc";
    public static final String USER_ID_QUEUE_NAME = "user_id";

    @Bean
    public Queue userIdQueue() {
        return new Queue(USER_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue plQueue() {
        return new Queue(PL_SYNC_QUEUE_NAME, false);
    }

    @Bean
    public Queue dlQueue() {
        return new Queue(DL_SYNC_QUEUE_NAME, false);
    }
}
