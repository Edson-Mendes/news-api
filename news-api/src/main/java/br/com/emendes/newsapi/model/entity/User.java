package br.com.emendes.newsapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * Classe que representa a entidade t_user do banco de dados.
 */
@Entity
@Table(name = "t_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  private Long id;
  @Column(length = 50, nullable = false)
  private String name;
  @Column(length = 320, nullable = false, unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(nullable = false)
  private boolean enabled;
  @Column(nullable = false)
  private LocalDateTime createdAt;
  @ManyToMany
  @JoinTable(
      name = "t_user_authorities",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "authority_id")}
  )
  private Collection<Authority> authorities;

  /**
   * Adiciona authority a collections authorities.
   *
   * @param authority Authority a ser adicinada.
   */
  public void addAuthority(Authority authority) {
    if (authorities == null) {
      authorities = new HashSet<>();
    }

    authorities.add(authority);
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return this.enabled;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

}
