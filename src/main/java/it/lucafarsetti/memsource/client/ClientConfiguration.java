package it.lucafarsetti.memsource.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClientConfiguration {

	private final ConfigParameter configParameter;

	public ClientConfiguration(@Value("${memsource.client.credential.username}") String username,
							   @Value("${memsource.client.credential.password}") String password) {

		this.configParameter = new ConfigParameter(username, password);
	}

	@Bean
	public MemsourceClient restClient() {
		return new MemsourceRestClient(this.configParameter);
	}

}
