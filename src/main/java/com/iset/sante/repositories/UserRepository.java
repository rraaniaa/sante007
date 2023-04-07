package com.iset.sante.repositories;

import com.iset.sante.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author Ramesh Fadatare
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>
{

	Optional<User> findByEmail(String email);

}
