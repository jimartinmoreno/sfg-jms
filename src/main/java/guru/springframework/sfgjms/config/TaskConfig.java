package guru.springframework.sfgjms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @EnableScheduling Enables Spring's scheduled task execution capability,
 * @EnableAsync Enables Spring's asynchronous method execution capability
 */
@EnableScheduling
@EnableAsync
@Configuration
public class TaskConfig {

    /**
     * TaskExecutor implementation that fires up a new Thread for each task, executing it asynchronously.
     * @return
     */
    @Bean
    TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor();
    }
}
