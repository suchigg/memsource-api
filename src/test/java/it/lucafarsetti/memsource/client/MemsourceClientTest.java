package it.lucafarsetti.memsource.client;

import it.lucafarsetti.memsource.account.AccountConfiguration;
import it.lucafarsetti.memsource.account.AccountService;
import it.lucafarsetti.memsource.projects.AccountConfigurationNotFoundException;
import it.lucafarsetti.memsource.projects.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Tag("MemsourceClient")
public class MemsourceClientTest {

	@InjectMocks
	private MemsourceClient client;

	@Mock
	private AccountService accountService;

	@Mock
	private RestTemplate restTemplate;

	@Test
	@DisplayName("Should throw AccountConfigurationNotFoundException when account is not configured")
	public void Should_ThrowAccountConfigurationNotFoundException_When_AccountIsNotConfigured() {
		when(accountService.find()).thenReturn(Optional.empty());

		Page page = new Page();
		assertThrows(AccountConfigurationNotFoundException.class, () -> this.client.findAllProjects(page));
		verifyNoInteractions(restTemplate);
	}

	@Test
	@DisplayName("Should return projects when account is configured and is valid")
	public void Should_ReturnProjects_When_AccountIsConfiguredAndIsValid() {
		ReflectionTestUtils.setField(client, "restTemplate", restTemplate);

		final var accountConfiguration = new AccountConfiguration("jhonDoe", "password");
		when(accountService.find()).thenReturn(Optional.of(accountConfiguration));

		final URI loginUrl = URI.create("https://cloud.memsource.com/web/api2/v1/auth/login");
		final var loggedAccount = new AuthTokenRetrieved();
		loggedAccount.setToken("tw0F4hCk4qFUJLespXsmt9dB5b0Gl7g9Jszn6il70FussmTIC1KGIKjG4wDrsHihb");

		when(restTemplate.postForEntity(loginUrl, accountConfiguration, AuthTokenRetrieved.class))
		  .thenReturn(ResponseEntity.ok(loggedAccount));

		RetrievedProjects retrievedProjects = getRetrievedProjects();
		final URI projectsUrl = URI.create("https://cloud.memsource.com/web/api2/v1/projects?pageNumber=0&pageSize=50");
		when(restTemplate.exchange(eq(projectsUrl),
								   eq(HttpMethod.GET),
								   ArgumentMatchers.<HttpEntity<String>>any(),
								   eq(RetrievedProjects.class)))
		  .thenReturn(ResponseEntity.ok(retrievedProjects));

		Page page = new Page();
		RetrievedProjects result = this.client.findAllProjects(page);

		verify(accountService, times(1)).find();
		verifyNoMoreInteractions(accountService);

		verify(restTemplate, times(1))
		  .postForEntity(loginUrl, accountConfiguration, AuthTokenRetrieved.class);
		verify(restTemplate, times(1))
		  .exchange(eq(projectsUrl),
					eq(HttpMethod.GET),
					ArgumentMatchers.<HttpEntity<String>>any(),
					eq(RetrievedProjects.class));
		verifyNoMoreInteractions(restTemplate);

		assertThat(result.getContent().size(), is(3));


	}

	private RetrievedProjects getRetrievedProjects() {
		var retrievedProjects = new RetrievedProjects();
		List<RetrievedProject> content = new ArrayList<>();
		var project1 = new RetrievedProject();
		ReflectionTestUtils.setField(project1, "name", "project1");
		content.add(project1);

		var project2 = new RetrievedProject();
		ReflectionTestUtils.setField(project2, "name", "project2");
		content.add(project2);

		var project3 = new RetrievedProject();
		ReflectionTestUtils.setField(project3, "name", "project3");
		content.add(project3);

		ReflectionTestUtils.setField(retrievedProjects, "pageNumber", 0);
		ReflectionTestUtils.setField(retrievedProjects, "numberOfElements", 3);
		ReflectionTestUtils.setField(retrievedProjects, "totalElements", 3);
		ReflectionTestUtils.setField(retrievedProjects, "pageSize", 50);
		ReflectionTestUtils.setField(retrievedProjects, "totalPages", 1);
		ReflectionTestUtils.setField(retrievedProjects, "content", content);

		return retrievedProjects;
	}

}
