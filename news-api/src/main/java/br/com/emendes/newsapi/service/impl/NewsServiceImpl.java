package br.com.emendes.newsapi.service.impl;

import br.com.emendes.newsapi.dto.request.CreateNewsRequest;
import br.com.emendes.newsapi.dto.response.NewsSummaryResponse;
import br.com.emendes.newsapi.mapper.NewsMapper;
import br.com.emendes.newsapi.model.entity.News;
import br.com.emendes.newsapi.repository.NewsRepository;
import br.com.emendes.newsapi.service.NewsService;
import br.com.emendes.newsapi.util.component.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Implementação de {@link NewsService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {

  private final NewsMapper newsMapper;
  private final AuthenticationFacade authenticationFacade;
  private final NewsRepository newsRepository;

  @Override
  public NewsSummaryResponse register(CreateNewsRequest createNewsRequest) {
    News news = newsMapper.toNews(createNewsRequest);

    news.setUser(authenticationFacade.getCurrentUser());
    news.setCreatedAt(LocalDateTime.now());

    news = newsRepository.save(news);

    log.info("news with id: {} registered successful", news.getId());
    return newsMapper.toNewsSummaryResponse(news);
  }

  @Override
  public Page<NewsSummaryResponse> fetch(Pageable pageable) {
    log.info("fetching page: {}, size {} of news", pageable.getPageNumber(), pageable.getPageSize());
    Page<News> newsPage = newsRepository.findAll(pageable);

    return newsPage.map(newsMapper::toNewsSummaryResponse);
  }

}
