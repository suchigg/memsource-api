package it.lucafarsetti.memsource.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountRestController {

	private final AccountService accountService;

	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/configuration")
	public ResponseEntity<AccountConfiguration> getConfiguration() {
		AccountConfiguration accountConfigurationFound = this.accountService.find().orElseGet(AccountConfiguration::new);
		return ResponseEntity.ok(accountConfigurationFound);
	}

	@PutMapping
	public ResponseEntity<URI> update(@Valid @RequestBody AccountConfiguration accountConfiguration) {
		this.accountService.save(accountConfiguration);
		return ResponseEntity.ok().build();
	}

}
