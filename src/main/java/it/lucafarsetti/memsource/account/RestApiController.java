package it.lucafarsetti.memsource.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PutMapping
	public ResponseEntity<URI> update(@RequestBody Account account) {
		Saved savedAccount = this.accountService.save(account);
		URI location = URI.create("/api/v1/accounts/" + savedAccount.getId().toString());
		return ResponseEntity.ok(location);
	}

}
