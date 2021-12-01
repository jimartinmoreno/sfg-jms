package guru.springframework.sfgjms.sender;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class HelloSender {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    //@Scheduled(fixedRate = 10000)
    public void sendMessage() throws JsonProcessingException {

        log.info("I'm Sending a message");

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World!")
                .build();

        log.info("objectMapper.writeValueAsString(message) = " + objectMapper.writeValueAsString(message));
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);

        log.info("Message Sent!");
    }

    @Scheduled(fixedRate = 10000)
    public void sendandReceiveMessage() throws JMSException {

        HelloWorldMessage message = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Nacho")
                .build();

        Message receivedMsg = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, session -> {
            Message helloMessage = null;

            try {
                helloMessage = session.createTextMessage(objectMapper.writeValueAsString(message));
                helloMessage.setStringProperty("_type", "guru.springframework.sfgjms.model.HelloWorldMessage");

                log.info("Sending Hello");

                return helloMessage;

            } catch (JsonProcessingException e) {
                throw new JMSException("boom");
            }
        });

        log.info("I Got a Message Back!!!!!");
        log.info(receivedMsg.getBody(String.class));
        log.info(receivedMsg.getBody(String.class));
    }
}
