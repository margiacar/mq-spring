package com.example.mqspring;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.AbstractJmsListenerContainerFactory;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@SpringBootApplication
@EnableJms
public class MqSpringApplication {

//	@Bean
//    public MQQueueConnectionFactory myFactory() {
//        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
//        mqQueueConnectionFactory.setHostName("localhost");
//        try {
//            mqQueueConnectionFactory.setTransportType(WMQConstants.ADMIN_QUEUE_DOMAIN);
//            mqQueueConnectionFactory.setCCSID(1208);
//            mqQueueConnectionFactory.setChannel("DEV.ADMIN.SVRCONN");
//            mqQueueConnectionFactory.setPort(1414);
//            mqQueueConnectionFactory.setQueueManager("QM1");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mqQueueConnectionFactory;
//    }
	
	@Bean
	@ConditionalOnProperty(prefix = "adapters", name = "jms", havingValue="true")
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        ((AbstractJmsListenerContainerFactory<?>)factory).setErrorHandler(new DefaultErrorHandler());
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        
        // You could still override some of Boot's default if necessary.
        return factory;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(MqSpringApplication.class, args);
	}
	
}
