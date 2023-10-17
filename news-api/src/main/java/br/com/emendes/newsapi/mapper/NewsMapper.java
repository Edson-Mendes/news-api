package br.com.emendes.newsapi.mapper;

import br.com.emendes.newsapi.dto.request.CreateNewsRequest;
import br.com.emendes.newsapi.dto.response.NewsSummaryResponse;
import br.com.emendes.newsapi.model.entity.News;

/**
 * Interface component com as abstrações pelo mapeamento do recurso News em DTOs e vice-versa.
 */
public interface NewsMapper {

  /**
   * Mapeia um DTO CreateNewsRequest para a entidade News.
   *
   * @param createNewsRequest objeto a ser mapeado para News.
   * @return um objeto News contendo as informações que estavam em createNewsRequest.
   */
  News toNews(CreateNewsRequest createNewsRequest);

  /**
   * Mapeia uma entidade News para o DTO NewsSummaryResponse.
   *
   * @param news objeto a ser mapeado para NewsSummaryResponse.
   * @return um objeto NewsSummaryResponse contendo informações que estavam em createNewsRequest.
   */
  NewsSummaryResponse toNewsSummaryResponse(News news);
}
