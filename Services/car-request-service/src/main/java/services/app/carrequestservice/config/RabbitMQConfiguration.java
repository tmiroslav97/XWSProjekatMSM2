package services.app.carrequestservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String AGENT_ID_BY_EMAIL_ID_QUEUE_NAME = "agent_id_by_email_id";
    public static final String AD_ACCEPT_REQ_QUEUE_NAME = "ad_accept_req";
    public static final String END_USER_IS_BLOCKED_ID_QUEUE_NAME = "end_user_is_blocked_id";
    public static final String AD_CAR_INFO_QUEUE_NAME = "ad_car_info";
    public static final String ACCEPT_REQUEST_QUEUE_NAME = "accept_request";
    public static final String END_USER_CANCELED_RENT_CNT_QUEUE_NAME = "user_submit_rent_cnt";
    public static final String USER_FL_NAME_QUEUE_NAME = "user_fl_name";

    @Bean
    public Queue userFLNameQueue() {
        return new Queue(USER_FL_NAME_QUEUE_NAME, false);
    }

    @Bean
    public Queue endUserCanceledRentCnt() {
        return new Queue(END_USER_CANCELED_RENT_CNT_QUEUE_NAME, false);
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
    public Queue endUserIsBlockedId() {
        return new Queue(END_USER_IS_BLOCKED_ID_QUEUE_NAME, false);
    }

    @Bean
    public Queue adAcceptReq() {
        return new Queue(AD_ACCEPT_REQ_QUEUE_NAME, false);
    }

    @Bean
    public Queue agentIdByEmailId() {
        return new Queue(AGENT_ID_BY_EMAIL_ID_QUEUE_NAME, false);
    }

}
