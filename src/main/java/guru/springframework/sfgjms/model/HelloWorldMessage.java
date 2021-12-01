package guru.springframework.sfgjms.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HelloWorldMessage implements Serializable {

    static final long serialVersionUID = -6703826490277916847L;

    private UUID id;
    private String message;
}
