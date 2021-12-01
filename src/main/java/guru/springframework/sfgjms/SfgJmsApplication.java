package guru.springframework.sfgjms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

    public static void main(String[] args) throws Exception {

        /**
         * Configuración de un servidor JMS Embebido
         *
         * This interface defines the internal interface of the ActiveMQ Artemis Server exposed to other components of
         * the server.
         */
        //		ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
        //			.setPersistenceEnabled(false)
        //			.setJournalDirectory("target/data/journal")
        //			.setSecurityEnabled(false)
        //			.addAcceptorConfiguration("invm", "vm://07"));
        //
        //		server.start();

        SpringApplication.run(SfgJmsApplication.class, args);
    }

}
