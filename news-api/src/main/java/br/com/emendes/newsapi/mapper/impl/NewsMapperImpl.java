package br.com.emendes.newsapi.mapper.impl;

import br.com.emendes.newsapi.dto.request.CreateNewsRequest;
import br.com.emendes.newsapi.dto.response.NewsSummaryResponse;
import br.com.emendes.newsapi.mapper.NewsMapper;
import br.com.emendes.newsapi.model.entity.News;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Implementação de {@link NewsMapper}.
 */
@Component
public class NewsMapperImpl implements NewsMapper {

  /**
   * @throws IllegalArgumentException caso createNewsRequest seja null.
   */
  @Override
  public News toNews(CreateNewsRequest createNewsRequest) {
    Assert.notNull(createNewsRequest, "createNewsRequest must not be null");

    return News.builder()
        .title(createNewsRequest.title())
        .content(createNewsRequest.content())
        .tag(createNewsRequest.tag())
        .verified(createNewsRequest.verified())
        .source(createNewsRequest.source())
        .build();
  }

  /**
   * @throws IllegalArgumentException caso news seja null.
   */
  @Override
  public NewsSummaryResponse toNewsSummaryResponse(News news) {
    Assert.notNull(news, "news must not be null");

    return NewsSummaryResponse.builder()
        .id(news.getId())
        .title(news.getTitle())
        .tag(news.getTag())
        .build();
  }

}
