package br.com.emendes.newsapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

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
public class Authority implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(length = 50, nullable = false, unique = true)
  private String name;

  @Override
  public String getAuthority() {
    return this.name;
  }
}
