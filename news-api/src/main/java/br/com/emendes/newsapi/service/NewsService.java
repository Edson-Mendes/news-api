package br.com.emendes.newsapi.service;

import br.com.emendes.newsapi.dto.request.CreateNewsRequest;
import br.com.emendes.newsapi.dto.response.NewsSummaryResponse;

/**
 * Interface service com as abstrações responsáveis pela manipulação do recurso News.
 */
public interface NewsService {

  /**
   * Registrar uma notícia no sistema.
   *
   * @param createNewsRequest DTO contendo as informações da notícia a ser registrada.
   * @return NewsSummaryResponse contendo informações resumidas da notícia registrada.
   */
  NewsSummaryResponse register(CreateNewsRequest createNewsRequest);

}
