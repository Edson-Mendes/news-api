package br.com.emendes.newsapi;

import br.com.emendes.newsapi.util.properties.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class})
public class NewsApplication {

  public static void main(String[] args) {
    SpringApplication.run(NewsApplication.class, args);
  }

}
