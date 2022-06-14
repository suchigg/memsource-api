package it.lucafarsetti.memsource.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Tag("Account")
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private AccountRepository repository;

	@Test
	@DisplayName("Should return saved account when is present")
	public void Should_ReturnSavedAccount_When_IsPresent() {
		final var memsourceAccount = new MemsourceAccount();
		memsourceAccount.setUsername("jhonDoe");
		memsourceAccount.setPassword("password");

		when(repository.findFirstBy()).thenReturn(Optional.of(memsourceAccount));

		final Optional<AccountConfiguration> result = accountService.find();

		verify(repository, times(1)).findFirstBy();
		verifyNoMoreInteractions(repository);


		assertThat(result.isPresent(), is(true));
		assertThat(result.get().getUserName(), is("jhonDoe"));
		assertThat(result.get().getPassword(), is("password"));
	}

	@Test
	@DisplayName("Should return empty account when is not present")
	public void Should_ReturnEmptyAccount_When_IsNotPresent() {
		when(repository.findFirstBy()).thenReturn(Optional.empty());

		final Optional<AccountConfiguration> result = accountService.find();

		verify(repository, times(1)).findFirstBy();
		verifyNoMoreInteractions(repository);

		assertThat(result.isPresent(), is(false));
	}

	@Test
	@DisplayName("Should insert a new account when is not yet configured")
	public void Should_InsertANewAccount_When_IsNotYetConfigured() {

		var savedAccount = new MemsourceAccount();
		final UUID id = UUID.fromString("9eec90fc-ebb2-11ec-8ea0-0242ac120002");
		ReflectionTestUtils.setField(savedAccount, "id", id);
		savedAccount.setUsername("jhonDoe");
		savedAccount.setPassword("password");

		when(repository.findFirstBy()).thenReturn(Optional.empty());
		when(repository.save(any(MemsourceAccount.class))).thenReturn(savedAccount);

		final var accountConfiguration = new AccountConfiguration("jhonDoe", "password");
		final Saved result = accountService.save(accountConfiguration);

		verify(repository, times(1)).findFirstBy();
		verify(repository, times(1)).save(any(MemsourceAccount.class));
		verifyNoMoreInteractions(repository);

		assertThat(result.getId(), is(id));
	}

	@Test
	@DisplayName("Should update the existing account when is already configured")
	public void Should_UpdateExistingAccount_When_IsAlreadyConfigured() {

		var savedAccount = new MemsourceAccount();
		final UUID id = UUID.fromString("9eec90fc-ebb2-11ec-8ea0-0242ac120002");
		ReflectionTestUtils.setField(savedAccount, "id", id);
		savedAccount.setUsername("jhonDoe");
		savedAccount.setPassword("password");

		when(repository.findFirstBy()).thenReturn(Optional.of(savedAccount));
		when(repository.save(savedAccount)).thenReturn(savedAccount);

		final var accountConfiguration = new AccountConfiguration("newUsername", "newPassword");
		final Saved result = accountService.save(accountConfiguration);

		verify(repository, times(1)).findFirstBy();
		verify(repository, times(1)).save(savedAccount);
		verifyNoMoreInteractions(repository);

		assertThat(savedAccount.getUsername(), is("newUsername"));
		assertThat(savedAccount.getPassword(), is("newPassword"));
		assertThat(result.getId(), is(id));
	}

}
