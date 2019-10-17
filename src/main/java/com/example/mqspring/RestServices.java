package com.example.mqspring;

import javax.jms.BytesMessage;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestServices {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@GetMapping("send")
	String send(@RequestParam String message) {
		try {
			jmsTemplate.convertAndSend("queue:///DEV.QUEUE.1?targetClient=1", message);
			return "OK";
		} catch(JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}
	
	@GetMapping("sendText")
	String sendText() {
		try {
			//jmsTemplate.convertAndSend("DEV.QUEUE.1", "test".getBytes());
			jmsTemplate.send("DEV.QUEUE.1", s -> {
				TextMessage message = s.createTextMessage();
				message.setText("Mensagem Texto");
				return message;
			});
			return "OK";
		} catch(JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}
	
	@GetMapping("sendBytes")
	String sendBytes() {
		try {
			//jmsTemplate.convertAndSend("DEV.QUEUE.1", "test".getBytes());
			jmsTemplate.send("DEV.QUEUE.1", s -> {
				BytesMessage message = s.createBytesMessage();
				message.writeBytes("Mensagem Binária".getBytes());
				return message;
			});
			return "OK";
		} catch(JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}
	
	@GetMapping("recv")
	String recv() {
		try {
			Message message = (Message)jmsTemplate.receiveAndConvert("DEV.QUEUE.1");
			return message.toString();
		} catch(JmsException ex) {
			ex.printStackTrace();
			return "FAIL";
		}
	}
}
