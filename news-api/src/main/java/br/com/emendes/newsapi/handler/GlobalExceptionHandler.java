package br.com.emendes.newsapi.handler;

import br.com.emendes.newsapi.exception.UserCreationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

/**
 * Controller advice responsável por lidar com exceptions que ocorrerem a partir dos controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    String fields = exception.getFieldErrors().stream()
        .map(FieldError::getField).collect(Collectors.joining("; "));
    String messages = exception.getFieldErrors().stream()
        .map(FieldError::getDefaultMessage).collect(Collectors.joining("; "));

    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));

    problemDetail.setTitle("Invalid fields");
    problemDetail.setDetail("Some fields are invalids");
    problemDetail.setProperty("fields", fields);
    problemDetail.setProperty("messages", messages);
    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/news-api/blob/main/README.md"));

    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(UserCreationException.class)
  public ResponseEntity<ProblemDetail> handleUserCreation(UserCreationException exception) {
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(400), exception.getMessage());
    problemDetail.setTitle("Bad request");
    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/news-api/blob/main/README.md"));

    return ResponseEntity.badRequest().body(problemDetail);
  }

}
