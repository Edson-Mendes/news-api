package br.com.emendes.newsapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Classe que representa a entidade t_news do banco de dados.
 */
@Entity
@Table(name = "t_news")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class News {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 150, nullable = false)
  private String title;
  @Column(nullable = false)
  private String content;
  @Column(length = 100, nullable = false)
  private String tag;
  @Column(nullable = false)
  private boolean verified;
  @Column(nullable = false)
  private String source;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @ManyToOne
  private User user;

}
