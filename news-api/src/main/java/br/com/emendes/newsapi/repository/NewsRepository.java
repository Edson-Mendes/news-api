package br.com.emendes.newsapi.repository;

import br.com.emendes.newsapi.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface repository com as abstrações para persistência do recurso News.
 */
public interface NewsRepository extends JpaRepository<News, Long> {
}
