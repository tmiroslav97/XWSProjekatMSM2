package services.app.adservice.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String USER_ID_QUEUE_NAME = "user_id";
    public static final String AD_SYNC_QUEUE_NAME = "ad_sync_rpc";
    public static final String AD_SEARCH_SYNC_QUEUE_NAME = "ad_search_sync";
    public static final String PL_NEW_EDIT_QUEUE_NAME = "pl_new_edit";
    public static final String PL_GET_QUEUE_NAME = "pl_get_queue";
    public static final String USER_FL_NAME_QUEUE_NAME = "user_fl_name";
    public static final String AD_ACCEPT_REQ_QUEUE_NAME = "ad_accept_req";
    public static final String AD_CAR_INFO_QUEUE_NAME = "ad_car_info";
    public static final String ACCEPT_REQUEST_QUEUE_NAME = "accept_request";
    public static final String CCT_SYNC_QUEUE_NAME = "cct_sync";
    public static final String AGENT_SYNC_QUEUE_NAME = "agent.sync";
    public static final String RATE_AD_QUEUE_NAME = "rate_ad";
    public static final String ADD_DISCOUNT_QUEUE_NAME = "add_discount";
    public static final String DELETE_DISCOUNT_QUEUE_NAME = "delete_discount";
    public static final String ADD_DISCOUNT_TO_AD_QUEUE_NAME = "add_discount_to_ad";
    public static final String DELETE_DISCOUNT_FROM_AD_QUEUE_NAME = "delete_discount_from_ad";
    public static final String AGENT_ID_BY_EMAIL_ID_QUEUE_NAME = "agent_id_by_email_id";

    @Bean
    public Queue rateAd() {
        return new Queue(RATE_AD_QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange topic() {
        return new TopicExchange(AGENT_SYNC_QUEUE_NAME);
    }

    @Bean
    public Queue cctSync() {
        return new Queue(CCT_SYNC_QUEUE_NAME, false);
    }

    @Bean
    public Queue acceptRequest() {
        return new Queue(ACCEPT_REQUEST_QUEUE_NAME, false);
    }

    @Bean
    public Queue adCarInfo() {
        return new Queue(AD_CAR_INFO_QUEUE_NAME, false);
    }

    @Bean
    public Queue adAcceptReq() {
        return new Queue(AD_ACCEPT_REQ_QUEUE_NAME, false);
    }

    @Bean
    public Queue userFLNameQueue() {
        return new Queue(USER_FL_NAME_QUEUE_NAME, false);
    }

    @Bean
    public Queue plGetQueue() {
        return new Queue(PL_GET_QUEUE_NAME, false);
    }

    @Bean
    public Queue plNewEditQueue() {
        return new Queue(PL_NEW_EDIT_QUEUE_NAME, false);
    }

    @Bean
    public Queue userIdQueue() {
        return new Queue(USER_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue adQueue() {
        return new Queue(AD_SYNC_QUEUE_NAME, false);
    }

    @Bean
    public Queue adSearchQueue() {
        return new Queue(AD_SEARCH_SYNC_QUEUE_NAME, false);
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue addDiscount() {
        return new Queue(ADD_DISCOUNT_QUEUE_NAME, false);
    }
    @Bean
    public Queue deleteDiscount() {
        return new Queue(DELETE_DISCOUNT_QUEUE_NAME, false);
    }

    @Bean
    public Queue addDiscountToAd() {
        return new Queue(ADD_DISCOUNT_TO_AD_QUEUE_NAME, false);
    }

    @Bean
    public Queue deleteDiscountFromAd() {
        return new Queue(DELETE_DISCOUNT_FROM_AD_QUEUE_NAME, false);
    }

    @Bean
    public Queue agentIdByEmailId() {
        return new Queue(AGENT_ID_BY_EMAIL_ID_QUEUE_NAME, false);
    }

}
