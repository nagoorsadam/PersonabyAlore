package com.plash.configurator.configuration;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * Created by Utsav on 11-Mar-17.
 */
@Configuration
@EnableJms
@ComponentScan
public class JmsConfiguration {

    private static final String JMS_BROKER_URL = "vm://embedded?broker.persistent=false,useShutdownHook=false";

   /* @Autowired
    private BeanFactory springContextBeanFactory;*/

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(JMS_BROKER_URL);
    }

    @Bean
    public DefaultJmsListenerContainerFactory containerFactory(ConnectionFactory connectionFactory,JmsErrorHandler errorHandler) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //factory.setDestinationResolver(new BeanFactoryDestinationResolver(springContextBeanFactory));
        factory.setConcurrency("3-10");
        factory.setErrorHandler(errorHandler);
        return factory;
    }
   @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) throws JMSException {
       java.security.Security.setProperty("networkaddress.cache.ttl","10");
        return new JmsTemplate(connectionFactory);
    }


  /*  @Bean
    public ConnectionFactory connectionFactory() {

        ConnectionFactory factory= new ActiveMQConnectionFactory(JMS_BROKER_URL);
        ((ActiveMQConnectionFactory)factory).setTrustAllPackages(true);
        ((ActiveMQConnectionFactory)factory).setOptimizeAcknowledge(true);
        ((ActiveMQConnectionFactory)factory).setAlwaysSessionAsync(false);

        return factory;
    }*/


}
