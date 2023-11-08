package br.com.emendes.newsapi.service;

import br.com.emendes.newsapi.dto.request.CreateUserRequest;
import br.com.emendes.newsapi.dto.response.UserDetailsResponse;
import br.com.emendes.newsapi.dto.response.UserSummaryResponse;
import br.com.emendes.newsapi.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface service com as abstrações responsáveis pela manipulação do recurso User.
 */
public interface UserService {

  /**
   * Cadastrar um usuário no sistema.
   *
   * @param userRequest DTO contendo as informações do usuário a ser cadastrado.
   * @return UserSummaryResponse contendo informações resumidas do usuário cadastrado.
   */
  UserSummaryResponse register(CreateUserRequest userRequest);

  /**
   * Busca paginada de usuários.
   *
   * @param pageable objeto com os parâmetros da paginação.
   * @return {@code Page<UserSummaryResponse>}
   */
  Page<UserSummaryResponse> fetch(Pageable pageable);

  /**
   * Buscar usuário por id.
   *
   * @param id identificador do usuário.
   * @return Objeto UserDetailsResponse com as informações detalhadas do usuário encontrado.
   * @throws UserNotFoundException caso o id fornecido não corresponda a nenhum usuário.
   */
  UserDetailsResponse findById(Long id);

}
