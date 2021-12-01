package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class HelloMessageListener {

    private final JmsTemplate jmsTemplate;

    /**
     * Consume mensajes de una cola
     *
     * @param helloWorldMessage
     * @param headers
     * @param message
     * @throws JMSException
     */
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(@Payload HelloWorldMessage helloWorldMessage,
                       @Headers MessageHeaders headers, Message message) throws JMSException {

        log.info("I Got a Message!!!!!");
        //log.info("message > " + message);
        log.info("message > " + message.getBody(String.class));
        //headers.entrySet().forEach((entry) -> log.info(entry.getKey() + ": " + entry.getValue()));
        log.info("helloWorldMessage > " + helloWorldMessage);
        log.info(helloWorldMessage.getMessage());

        // uncomment and view to see retry count in debugger
        // throw new RuntimeException("foo");
    }

    /**
     * Consume mensajes de una cola y reenvia un mensaje de vuelta a la cola message.getJMSReplyTo()
     *
     * @param helloWorldMessage
     * @param headers
     * @param message
     * @throws JMSException
     */

    @JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage,
                               @Headers MessageHeaders headers, Message message) throws JMSException {

        log.info("I Got a Message!!!!!");
        //log.info("message > " + message);
        log.info("message > " + message.getBody(String.class));
        //headers.entrySet().forEach((entry) -> log.info(entry.getKey() + ": " + entry.getValue()));
        log.info("helloWorldMessage > " + helloWorldMessage.getMessage());

        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello " + helloWorldMessage.getMessage() + "!!")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);
    }
}
