package services.app.adsearchservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String USER_ID_QUEUE_NAME = "user_id";
    public static final String AD_SEARCH_SYNC_QUEUE_NAME = "ad_search_sync";
    public static final String USER_FL_NAME_QUEUE_NAME = "user_fl_name";

    @Bean
    public Queue userFLNameQueue() {
        return new Queue(USER_FL_NAME_QUEUE_NAME, false);
    }

    @Bean
    public Queue userIdQueue() {
        return new Queue(USER_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue adSearchQueue() {
        return new Queue(AD_SEARCH_SYNC_QUEUE_NAME, false);
    }

}
