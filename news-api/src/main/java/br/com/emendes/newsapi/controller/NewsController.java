package br.com.emendes.newsapi.controller;

import br.com.emendes.newsapi.dto.request.CreateNewsRequest;
import br.com.emendes.newsapi.dto.response.NewsSummaryResponse;
import br.com.emendes.newsapi.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/news/**.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {

  private final NewsService newsService;

  /**
   * Método responsável por POST /api/news.
   */
  @PostMapping
  public ResponseEntity<NewsSummaryResponse> register(
      @RequestBody @Valid CreateNewsRequest createNewsRequest, UriComponentsBuilder uriBuilder) {
    NewsSummaryResponse newsSummaryResponse = newsService.register(createNewsRequest);

    URI location = uriBuilder.path("/api/news/{id}").build(newsSummaryResponse.id());

    return ResponseEntity.created(location).body(newsSummaryResponse);
  }

}
