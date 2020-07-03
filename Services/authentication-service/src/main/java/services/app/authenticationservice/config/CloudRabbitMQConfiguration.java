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
public class CloudRabbitMQConfiguration {

    @Value("${spring.rabbitmq.addresses.cloud}")
    private String cloud;

    public static final String QUEUE_NAME = "emails";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean(name = "cloudConnectionFactory")
    public ConnectionFactory cloudConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUri(cloud);
        return connectionFactory;
    }

    @Bean(name = "cloudRabbitTemplate")
    public RabbitTemplate cloudRabbitTemplate(@Qualifier("cloudConnectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }

    @Bean(name = "cloudFactory")
    public SimpleRabbitListenerContainerFactory cloudFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
                                                             @Qualifier("cloudConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean(name = "cloudRabbitAdmin")
    public RabbitAdmin cloudRabbitAdmin(@Qualifier("cloudConnectionFactory") ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
