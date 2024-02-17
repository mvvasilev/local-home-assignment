package dev.mvvasilev.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RemoteTemplateConfiguration {

    @Value("${remote.root-uri}")
    String remoteRootUri;

    @Bean
    public RestTemplate googleApisTemplate() {
        return new RestTemplateBuilder()
                .rootUri(remoteRootUri)
                .build();
    }

}
