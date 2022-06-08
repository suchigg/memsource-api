package it.lucafarsetti.memsource.account;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {


	private final AccountRepository repository;

	public AccountService(AccountRepository repository) {
		this.repository = repository;
	}

	public Optional<AccountConfiguration> find() {
		return this.repository.findFirstBy()
							  .map(accountFound -> new AccountConfiguration(accountFound.getUsername(), accountFound.getPassword()));
	}

	public Saved save(AccountConfiguration accountConfiguration) {
		MemsourceAccount accountToSave = this.repository.findFirstBy().orElseGet(MemsourceAccount::new);

		accountToSave.setUsername(accountConfiguration.getUserName());
		accountToSave.setPassword(accountConfiguration.getPassword());

		MemsourceAccount savedAccount = this.repository.save(accountToSave);
		return new Saved(savedAccount.getId());
	}

}
