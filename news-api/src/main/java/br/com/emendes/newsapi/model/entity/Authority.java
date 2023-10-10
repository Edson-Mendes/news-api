package br.com.emendes.newsapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Classe que representa a entidade t_authority do banco de dados.
 */
@Entity
@Table(name = "t_authority")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length = 50, nullable = false, unique = true)
  private String name;

}
