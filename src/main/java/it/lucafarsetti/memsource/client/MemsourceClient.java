package it.lucafarsetti.memsource.client;

import it.lucafarsetti.memsource.account.AccountConfiguration;
import it.lucafarsetti.memsource.account.AccountService;
import it.lucafarsetti.memsource.projects.AccountConfigurationNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class MemsourceClient {

	private final AccountService accountService;
	private final RestTemplate restTemplate;

	public MemsourceClient(AccountService accountService) {
		this.accountService = accountService;
		this.restTemplate = new RestTemplate();
	}


	public RetrievedProjects findAllProjects() {

		var uri = URI.create("https://cloud.memsource.com/web/api2/v1/projects");
		MultiValueMap<String,String> authHeader = getAuthHeader();
		ResponseEntity<RetrievedProjects> retrievedProjects = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(authHeader), RetrievedProjects.class);

		return retrievedProjects.getBody();
	}

	private MultiValueMap<String,String> getAuthHeader() {
		AuthTokenRetrieved tokenRetrieved = authenticate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String authToken = new StringBuilder()
							 .append("ApiToken ")
							 .append(tokenRetrieved.getToken())
							 .toString();

		headers.set("Authorization", authToken);

		return headers;
	}

	private AuthTokenRetrieved authenticate() {
		AccountConfiguration account = this.accountService.find()
														  .orElseThrow(AccountConfigurationNotFoundException::new);

		var uri = URI.create("https://cloud.memsource.com/web/api2/v1/auth/login");
		ResponseEntity<AuthTokenRetrieved> tokenRetrieved = restTemplate.postForEntity(uri, account, AuthTokenRetrieved.class);

		return tokenRetrieved.getBody();
	}

}
