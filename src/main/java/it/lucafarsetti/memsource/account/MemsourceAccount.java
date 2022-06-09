package it.lucafarsetti.memsource.account;

import org.hibernate.Hibernate;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "accounts")
@Table(uniqueConstraints = {
  @UniqueConstraint(name = "uc_memsourceaccount_username", columnNames = {"username"})
})
public class MemsourceAccount {

	@Id
	@GeneratedValue
	@Column(columnDefinition = "BINARY(16)", updatable = false)
	@Type(type="org.hibernate.type.UUIDBinaryType")
	private UUID id;

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;


	public UUID getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		MemsourceAccount that = (MemsourceAccount) o;
		return id != null && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}
