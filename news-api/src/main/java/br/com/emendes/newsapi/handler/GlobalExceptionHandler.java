package br.com.emendes.newsapi.handler;

import br.com.emendes.newsapi.exception.UserCreationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

/**
 * Controller advice responsável por lidar com exceptions que ocorrerem a partir dos controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UserCreationException.class)
  public ResponseEntity<ProblemDetail> handleUserCreation(UserCreationException exception) {
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(400), exception.getMessage());
    problemDetail.setTitle("Bad request");
    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/news-api/blob/main/README.md"));

    return ResponseEntity.badRequest().body(problemDetail);
  }

}
