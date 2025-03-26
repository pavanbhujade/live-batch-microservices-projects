package com.substring.foodie.payment.fubctions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Consumer;

@Configuration
public class OrderNotification {

    @Autowired
    StreamBridge streamBridge;

    @Bean
    public Consumer<Message<OrderDTO>> orderEvents() {
        return message -> {

            OrderDTO orderDTO = message.getPayload();

            System.out.println("Order Event received");
            System.out.println("delivery process started ");
            System.out.println("order id " + orderDTO.orderId());
            System.out.println("order status " + orderDTO.status());
            System.out.println("order amount " + orderDTO.amount());
            System.out.println("_____fine_____");

            //publish the event
            sendAcknowledgement(orderDTO.orderId());
        };
    }


    public void sendAcknowledgement(String orderID) {

        String ackMessage = "We have received the orderID : " + orderID;
        Message<String> message = MessageBuilder.withPayload(ackMessage).build();

        streamBridge.send("orderAcknowledge-out-1", message);


    }
}
