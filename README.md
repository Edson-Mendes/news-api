# News

![Badge Em Desenvolvimento](https://img.shields.io/static/v1?label=Status&message=Em%20Desenvolvimento&color=yellow&style=for-the-badge)
![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=437291&style=for-the-badge&logo=openjdk&logoColor=437291)
![Badge Springboot](https://img.shields.io/static/v1?label=Springboot&message=v3.1.4&color=6DB33F&style=for-the-badge&logo=spring)

## Resumo
Aplicação em que usuários podem cadastrar notícias, receber notificação de novas notícias por email.

## Tecnologias e ferramentas

<a href="https://www.jetbrains.com/idea/" target="_blank"><img src="https://img.shields.io/badge/intellij-000000.svg?&style=for-the-badge&logo=intellijidea&logoColor=white" target="_blank"></a>

<a href="https://pt.wikipedia.org/wiki/Java_(linguagem_de_programa%C3%A7%C3%A3o)" target="_blank"><img src="https://img.shields.io/badge/java%2017-D32323.svg?&style=for-the-badge&logo=java&logoColor=white" target="_blank"></a>

<a href="https://spring.io/projects/spring-boot" target="_blank"><img src="https://img.shields.io/badge/Springboot-6db33f.svg?&style=for-the-badge&logo=springboot&logoColor=white" target="_blank"></a>

<a href="https://projectlombok.org/" target="_blank"><img src="https://img.shields.io/badge/Lombok-a4a4a4.svg?&style=for-the-badge&logo=lombok&logoColor=black" target="_blank"></a>

## Diagramas

### Diagrama entidade relacionamento

```mermaid
---
    title: Database Schema
---
    erDiagram
        USER {
            bigserial id PK
            varchar name
            varchar email
            varchar password
            boolean enabled
            timestamp created_at
        }
        ROLE {
            serial id PK
            varchar name
        }
        USER_ROLES {
            bigint user_id FK
            int role_id FK
        }
        NEWS {
            bigserial id PK
            varchar title
            text content
            varchar tag
            boolean verified
            varchar source
            timestamp created_at
            bigint user_id FK
        }
        
        USER }o--o{ USER_ROLES : has
        ROLE }o--o{ USER_ROLES : allows
        USER ||--o{ NEWS : publishes
```
