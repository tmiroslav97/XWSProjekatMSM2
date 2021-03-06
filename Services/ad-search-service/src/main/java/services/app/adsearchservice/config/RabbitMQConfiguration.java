package services.app.adsearchservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    public static final String USER_ID_QUEUE_NAME = "user_id";
    public static final String AD_SEARCH_SYNC_QUEUE_NAME = "ad_search_sync";
    public static final String USER_FL_NAME_QUEUE_NAME = "user_fl_name";
    public static final String CCT_SYNC_QUEUE_NAME = "cct_sync";
    public static final String UPDATE_PL_AD_SEARCH_QUEUE_NAME = "update_pl_ad_search";
    public static final String UPDATE_MILEAGE_AD_SEARCH_QUEUE_NAME = "update_mileage_ad_search";
    public static final String DELETE_AD_SEARCH_QUEUE_NAME = "delete_ad_search";
    public static final String REVERT_AD_SEARCH_QUEUE_NAME = "revert_ad_search";

    @Bean
    public Queue revertAdSearch() {
        return new Queue(REVERT_AD_SEARCH_QUEUE_NAME, false);
    }

    @Bean
    public Queue deleteAdSerch() {
        return new Queue(DELETE_AD_SEARCH_QUEUE_NAME, false);
    }


    @Bean
    public Queue updateMileageAdSearch() {
        return new Queue(UPDATE_MILEAGE_AD_SEARCH_QUEUE_NAME, false);
    }

    @Bean
    public Queue updatePlAdSearch() {
        return new Queue(UPDATE_PL_AD_SEARCH_QUEUE_NAME, false);
    }

    @Bean
    public Queue ccySync() {
        return new Queue(CCT_SYNC_QUEUE_NAME, false);
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
    public Queue adSearchQueue() {
        return new Queue(AD_SEARCH_SYNC_QUEUE_NAME, false);
    }

}
