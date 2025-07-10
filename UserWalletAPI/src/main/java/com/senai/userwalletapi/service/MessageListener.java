package com.senai.userwalletapi.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida: " + message);
    }

}
