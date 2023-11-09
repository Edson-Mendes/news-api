package br.com.emendes.newsapi.repository;

import br.com.emendes.newsapi.dto.response.UserDetailsResponse;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Interface repository com as abstrações para persistência do recurso User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Busca um usuário por email.
   *
   * @param email E-mail do usuário a ser buscado.
   * @return {@code Optional<User>} objeto wrapper de User.
   */
  Optional<User> findByEmail(String email);

  /**
   * Busca paginada de Usuários. Busca apenas os campos que pertencem a {@link UserSummaryResponse}.
   *
   * @param pageable objeto contendo os parâmetros de paginação.
   * @return {@code Page<UserSummaryResponse>} resumo paginado dos usuários.
   */
  Page<UserSummaryResponse> findBy(Pageable pageable);

  /**
   * Busca usuário por id. Busca apenas os campos que pertencem a {@link UserDetailsResponse}.
   *
   * @param id identificador do usuário a ser buscado.
   * @return {@code Optional<UserDetailsResponse>}
   */
  Optional<UserDetailsResponse> findProjectedById(Long id);

}
