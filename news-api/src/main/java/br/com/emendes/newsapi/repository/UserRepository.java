package br.com.emendes.newsapi.repository;

import br.com.emendes.newsapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface repository com as abstrações para persistência do recurso User.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
