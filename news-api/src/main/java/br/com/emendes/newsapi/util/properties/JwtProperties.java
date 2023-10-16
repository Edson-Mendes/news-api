package br.com.emendes.newsapi.util.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "newsapi.jwt")
public record JwtProperties(
    String secret,
    long expiration
) {
}

