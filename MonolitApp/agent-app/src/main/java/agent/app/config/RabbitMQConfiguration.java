package agent.app.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String AUTH_SYNC_QUEUE_NAME = "auth_sync_rpc";
    public static final String AD_SYNC_QUEUE_NAME = "ad_sync_rpc";
    public static final String PL_SYNC_QUEUE_NAME = "pl_sync_rpc";
    public static final String DL_SYNC_QUEUE_NAME = "dl_sync_rpc";
    public static final String AGENT_SYNC_QUEUE_NAME = "agent.sync";

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(AGENT_SYNC_QUEUE_NAME);
    }


    @Bean
    public Queue autoDeleteQueueCarCalendarTerm() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteRequest() {
        return new AnonymousQueue();
    }

    @Bean
    public Queue autoDeleteRateAd() {
        return new AnonymousQueue();
    }

    @Bean
    public Binding bidingRateAd(TopicExchange topic,
                                Queue autoDeleteRateAd) {
        return BindingBuilder.bind(autoDeleteRateAd)
                .to(topic)
                .with("tomic.miroslav97.gmail.com.rate");
    }

    @Bean
    public Binding bindingRequest(TopicExchange topic,
                                  Queue autoDeleteRequest) {
        return BindingBuilder.bind(autoDeleteRequest)
                .to(topic)
                .with("tomic.miroslav97.gmail.com.req");
    }

    @Bean
    public Binding bindingCarCalendarTerm(TopicExchange topic,
                                          Queue autoDeleteQueueCarCalendarTerm) {
        return BindingBuilder.bind(autoDeleteQueueCarCalendarTerm)
                .to(topic)
                .with("tomic.miroslav97.gmail.com.cct");
    }

    @Bean
    public Queue authQueue() {
        return new Queue(AUTH_SYNC_QUEUE_NAME, false);
    }

    @Bean
    public Queue adQueue() {
        return new Queue(AD_SYNC_QUEUE_NAME, false);
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
