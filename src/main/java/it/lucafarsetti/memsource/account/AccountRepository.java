package it.lucafarsetti.memsource.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<MemsourceAccount, UUID> {

	Optional<MemsourceAccount> findFirstBy();

}
