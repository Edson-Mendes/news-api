package br.com.emendes.newsapi.repository;

import br.com.emendes.newsapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Interface repository com as abstrações para persistência do recurso User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Busca um usuário por username (email) e que esteja ativo.
   *
   * @param username email do usuário a ser buscado.
   * @return {@code Optional<UserDetails>}.
   */
  Optional<UserDetails> findByEmailAndEnabledTrue(String username);

  /**
   * Busca um usuário por email.
   *
   * @param email E-mail do usuário a ser buscado.
   * @return {@code Optional<User>} objeto wrapper de User.
   */
  Optional<User> findByEmail(String email);

}
