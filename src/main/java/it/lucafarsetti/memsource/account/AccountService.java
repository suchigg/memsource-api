package it.lucafarsetti.memsource.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {


	private final AccountRepository repository;

	public AccountService(AccountRepository repository) {
		this.repository = repository;
	}

	public Saved save(Account account) {
		MemsourceAccount accountToSave = this.repository.findFirstBy().orElseGet(MemsourceAccount::new);

		accountToSave.setUsername(account.getUsername());
		accountToSave.setPassword(account.getPassword());

		MemsourceAccount savedAccount = this.repository.save(accountToSave);
		return new Saved(savedAccount.getId());
	}

}
