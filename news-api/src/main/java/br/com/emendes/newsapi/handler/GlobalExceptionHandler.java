package br.com.emendes.newsapi.handler;

import br.com.emendes.newsapi.exception.UserCreationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
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

  public static final URI URI = java.net.URI.create("https://github.com/Edson-Mendes/news-api/blob/main/README.md");

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
    problemDetail.setType(URI);

    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(UserCreationException.class)
  public ResponseEntity<ProblemDetail> handleUserCreation(UserCreationException exception) {
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(400), exception.getMessage());
    problemDetail.setTitle("Bad request");
    problemDetail.setType(URI);

    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ProblemDetail> handleBadCredentials(BadCredentialsException exception, WebRequest request) {
    ProblemDetail problemDetail = createProblemDetail(
        exception, HttpStatusCode.valueOf(400),
        "invalid e-mail or password", null, null, request);

    problemDetail.setTitle(exception.getMessage());
    problemDetail.setType(URI);

    return ResponseEntity.internalServerError().body(problemDetail);
  }

  @ExceptionHandler(LockedException.class)
  public ResponseEntity<ProblemDetail> handleLocked(LockedException exception, WebRequest request) {
    ProblemDetail problemDetail = createProblemDetail(
        exception, HttpStatusCode.valueOf(400),
        "account non activated, please check your email to activate your account",
        null, null, request);

    problemDetail.setTitle(exception.getMessage());
    problemDetail.setType(URI);

    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ProblemDetail> handleRuntimeException(RuntimeException exception, WebRequest request) {
    String message = "%s, Please read the logs for more info.".formatted(exception.getMessage());
    ProblemDetail problemDetail = createProblemDetail(exception, HttpStatusCode.valueOf(500), message, null, null, request);

    problemDetail.setTitle("Something went wrong");
    problemDetail.setType(URI);

    return ResponseEntity.internalServerError().body(problemDetail);
  }

}
