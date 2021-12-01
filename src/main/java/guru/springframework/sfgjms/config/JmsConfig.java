package guru.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Created by jt on 2019-07-17.
 */
@Configuration
public class JmsConfig {

    // Nombre de la cola a la que vamos a enviar los mensajes
    public static final String MY_QUEUE = "my-hello-world";
    // Nombre de la cola a la que vamos a enviar los mensajes de ida y vuelta
    public static final String MY_SEND_RCV_QUEUE = "replybacktome";

    /**
     * Strategy interface that specifies a converter between Java objects and JMS messages.
     *
     * @return MessageConverter
     */
    @Bean
    public MessageConverter messageConverter() {

        /**
         * Message converter that uses Jackson 2.x to convert messages to and from JSON. Maps an object to a BytesMessage,
         * or to a TextMessage if the targetType is set to MessageType.TEXT. Converts from a TextMessage or BytesMessage
         * to an object.
         */
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT); /* Constants that indicate a target message type to convert to:
                                                    a javax.jms.TextMessage, a javax.jms.BytesMessage,
                                                    a javax.jms.MapMessage or an javax.jms.ObjectMessage.*/
        converter.setTypeIdPropertyName("_type"); /* Specify the name of the JMS message property that carries the type
                                                    id for the contained object: either a mapped id value or a raw Java
                                                    class name.*/
        return converter;
    }
}
