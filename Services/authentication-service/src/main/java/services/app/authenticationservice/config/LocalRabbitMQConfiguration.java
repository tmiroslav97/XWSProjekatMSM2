package services.app.authenticationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class LocalRabbitMQConfiguration {

    @Value("${spring.rabbitmq.addresses.local}")
    protected String local;

    public static final String AUTH_SYNC_QUEUE_NAME = "auth_sync_rpc";
    public static final String USER_ID_QUEUE_NAME = "user_id";
    public static final String USER_FL_NAME_QUEUE_NAME = "user_fl_name";
    public static final String AGENT_ID_BY_EMAIL_ID_QUEUE_NAME = "agent_id_by_email_id";
    public static final String END_USER_IS_BLOCKED_ID_QUEUE_NAME = "end_user_is_blocked_id";
    public static final String END_USER_IS_OBLIGED_ID_QUEUE_NAME = "end_user_is_obliged_id";
    public static final String END_USER_CANCELED_RENT_CNT_QUEUE_NAME = "user_submit_rent_cnt";

    @Bean
    public Queue endUserCanceledRentCnt() {
        return new Queue(END_USER_CANCELED_RENT_CNT_QUEUE_NAME, false);
    }

    @Bean
    public Queue endUserIsObligedId() {
        return new Queue(END_USER_IS_OBLIGED_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue endUserIsBlockedId() {
        return new Queue(END_USER_IS_BLOCKED_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue agentIdByEmailId() {
        return new Queue(AGENT_ID_BY_EMAIL_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue userFLNameQueue() {
        return new Queue(USER_FL_NAME_QUEUE_NAME, false);
    }

    @Bean
    public Queue userIdQueue() {
        return new Queue(USER_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue authQueue() {
        return new Queue(AUTH_SYNC_QUEUE_NAME, false);
    }

    @Bean(name = "localConnectionFactory")
    @Primary
    public ConnectionFactory localConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri(local);
        return connectionFactory;
    }

    @Bean(name = "localRabbitTemplate")
    @Primary
    public RabbitTemplate localRabbitTemplate(@Qualifier("localConnectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name = "localFactory")
    public SimpleRabbitListenerContainerFactory localFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                             @Qualifier("localConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name = "localRabbitAdmin")
    public RabbitAdmin localRabbitAdmin(@Qualifier("localConnectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
