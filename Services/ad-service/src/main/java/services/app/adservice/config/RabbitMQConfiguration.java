package services.app.adservice.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Queue;

@Configuration
public class RabbitMQConfiguration {

    public static final String KEYSTORE_PROVIDER = "SunX509";
    public static final String QUEUE_NAME = "logs";

//    /**
//     * TLS version.
//     */
//    @Value("${TLS_VERSION:TLSv1.2}")
//    private String algorithm;
//
//    /**
//     * Application keystore path.
//     */
//    @Value("${KEYSTORE:logan.keystore.p12}")
//    private String keystore;
//
//    /**
//     * Application keystore type.
//     */
//    @Value("${KEYSTORE_TYPE:PKCS12}")
//    private String keystoreType;
//
//    /**
//     * Application keystore password.
//     */
//    @Value("${KEYSTORE_PASSWORD:password}")
//    private String keystorePassword;
//
//    /**
//     * Keystore alias for application client credential.
//     */
//    @Value("${KEYSTORE_ALIAS:logan}")
//    private String applicationKeyAlias;
//
//    /**
//     * Application truststore path.
//     */
//    @Value("${TRUSTSTORE:logan.truststore.p12}")
//    private String truststore;
//
//    /**
//     * Application truststore type.
//     */
//    @Value("${TRUSTSTORE_TYPE:PKCS12}")
//    private String truststoreType;
//
//    /**
//     * Application truststore password.
//     */
//    @Value("${TRUSTSTORE_PASSWORD:password}")
//    private String truststorePassword;
//
//    @Value("${RMQ_HOST:localhost}")
//    private String host;
//
//    @Value("${RMQ_PORT:5671}")
//    private String port;


//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME, false);
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//
//        try {
//            KeyStore keyStore = KeyStore.getInstance(keystoreType);
//            keyStore.load(new FileInputStream(new File(keystore)), keystorePassword.toCharArray());
//
//            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KEYSTORE_PROVIDER);
//            kmf.init(keyStore, keystorePassword.toCharArray());
//
//            KeyStore trustStore = KeyStore.getInstance(truststoreType);
//            trustStore.load(new FileInputStream(new File(truststore)), truststorePassword.toCharArray());
//
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(KEYSTORE_PROVIDER);
//            tmf.init(trustStore);
//
//            SSLContext sslcontext = SSLContext.getInstance(algorithm);
//            sslcontext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
//
//            ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
//            factory.setHost(host);
//            factory.setPort(Integer.parseInt(port));
//            factory.useSslProtocol(sslcontext);
//
//            return factory;
//
//        } catch (Exception e) {
//            throw new IllegalStateException("Error while configuring rabbitmq template", e);
//        }
//    }
}
