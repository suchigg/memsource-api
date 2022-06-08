package it.lucafarsetti.memsource.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/accounts")
public class RestApiController {

	private final AccountService accountService;

	public RestApiController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/configuration")
	public ResponseEntity<AccountConfiguration> getConfiguration() {
		AccountConfiguration accountConfigurationFound = this.accountService.find();
		return ResponseEntity.ok(accountConfigurationFound);
	}

	@PutMapping
	public ResponseEntity<URI> update(@RequestBody AccountConfiguration accountConfiguration) {
		this.accountService.save(accountConfiguration);
		return ResponseEntity.ok().build();
	}

}
