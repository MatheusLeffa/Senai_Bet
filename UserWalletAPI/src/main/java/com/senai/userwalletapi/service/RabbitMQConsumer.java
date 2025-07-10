package com.senai.userwalletapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senai.userwalletapi.dto.PaymentDto;
import com.senai.userwalletapi.dto.MessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private final WalletService walletService;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQConsumer(WalletService walletService) {
        this.walletService = walletService;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(@Payload MessageDto message) {
        try {
            if (message.getEvent().equals("PAYMENT")) {
                PaymentDto paymentDto = objectMapper.convertValue(message.getData(), PaymentDto.class);
                walletService.addCreditToWallet(paymentDto.getUser(), paymentDto.getPaymentValue());
            }
        } catch (Exception e) {
            if (message.getEvent().equals("PAYMENT")) {
                System.out.println(e.getMessage());
            }
        }
    }

}
