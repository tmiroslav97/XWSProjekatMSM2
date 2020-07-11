package services.app.pricelistanddiscountservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String PL_SYNC_QUEUE_NAME = "pl_sync_rpc";
    public static final String DL_SYNC_QUEUE_NAME = "dl_sync_rpc";
    public static final String USER_ID_QUEUE_NAME = "user_id";
    public static final String PL_NEW_EDIT_QUEUE_NAME = "pl_new_edit";
    public static final String PL_GET_QUEUE_NAME = "pl_get_queue";
    public static final String AGENT_ID_BY_EMAIL_ID_QUEUE_NAME = "agent_id_by_email_id";
    public static final String ADD_DISCOUNT_QUEUE_NAME = "add_discount";
    public static final String DELETE_DISCOUNT_QUEUE_NAME = "delete_discount";
    public static final String ADD_DISCOUNT_TO_AD_QUEUE_NAME = "add_discount_to_ad";
    public static final String DELETE_DISCOUNT_FROM_AD_QUEUE_NAME = "delete_discount_from_ad";
    public static final String DISCOUNT_INFO_BY_ID_QUEUE_NAME = "discount_info_by_id";


    @Bean
    public Queue discountInfoById() {
        return new Queue(DISCOUNT_INFO_BY_ID_QUEUE_NAME, false);
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
    public Queue plQueue() {
        return new Queue(PL_SYNC_QUEUE_NAME, false);
    }

    @Bean
    public Queue dlQueue() {
        return new Queue(DL_SYNC_QUEUE_NAME, false);
    }

    @Bean
    public Queue agentIdByEmailId() {
        return new Queue(AGENT_ID_BY_EMAIL_ID_QUEUE_NAME, false);
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

}
