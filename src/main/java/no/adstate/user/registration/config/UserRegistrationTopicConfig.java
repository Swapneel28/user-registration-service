package no.adstate.user.registration.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class UserRegistrationTopicConfig {

	@Bean
    public NewTopic userRegistartionTopic() {
    	return TopicBuilder.name("user-registration").build();
    }
}
