package com.example.mqspring;

import javax.jms.TextMessage;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "adapters", name = "jms", havingValue="true")
public class Receiver {

	//@JmsListener(destination = "DEV.QUEUE.1", containerFactory = "myFactory")
	@JmsListener(destination = "DEV.QUEUE.1, DEV.QUEUE.2", containerFactory = "myFactory")
    //public void receiveMessage(byte[] message) throws Exception {
	public void receiveMessage(TextMessage message) throws Exception {
        System.out.println("Received <" + message + ">");
		//throw new MessageException("error");
    }
}