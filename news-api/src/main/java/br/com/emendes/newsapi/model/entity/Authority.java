package br.com.emendes.newsapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Classe que representa a entidade t_authority do banco de dados.
 */
@Entity
@Table(name = "t_authority")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Integer id;
  @Column(length = 50, nullable = false, unique = true)
  private String name;

}
