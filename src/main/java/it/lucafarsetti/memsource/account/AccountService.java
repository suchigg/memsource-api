package it.lucafarsetti.memsource.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {


	private final AccountRepository repository;

	public AccountService(AccountRepository repository) {
		this.repository = repository;
	}

	public AccountConfiguration find() {
		return this.repository.findFirstBy()
							  .map(accountFound -> new AccountConfiguration(accountFound.getUsername(), accountFound.getPassword()))
							  .orElseGet(AccountConfiguration::new);
	}

	public Saved save(AccountConfiguration accountConfiguration) {
		MemsourceAccount accountToSave = this.repository.findFirstBy().orElseGet(MemsourceAccount::new);

		accountToSave.setUsername(accountConfiguration.getUsername());
		accountToSave.setPassword(accountConfiguration.getPassword());

		MemsourceAccount savedAccount = this.repository.save(accountToSave);
		return new Saved(savedAccount.getId());
	}

}
