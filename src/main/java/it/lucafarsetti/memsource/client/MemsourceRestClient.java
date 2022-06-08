package it.lucafarsetti.memsource.client;

import org.springframework.web.client.RestTemplate;

public class MemsourceRestClient implements MemsourceClient{

	private final ConfigParameter configParameter;
	private final RestTemplate restTemplate;

	public MemsourceRestClient(ConfigParameter configParameter) {
		this.configParameter = configParameter;
		this.restTemplate = new RestTemplate();
	}

}
