package br.com.emendes.newsapi.controller;

import br.com.emendes.newsapi.dto.request.CreateNewsRequest;
import br.com.emendes.newsapi.dto.response.NewsSummaryResponse;
import br.com.emendes.newsapi.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

  /**
   * Método responsável por GET /api/news.
   */
  @GetMapping
  public ResponseEntity<Page<NewsSummaryResponse>> fetch(@PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
    return ResponseEntity.ok(newsService.fetch(pageable));
  }

}
