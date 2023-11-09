# Lista do que ainda planejo fazer nesse projeto.

## TODO
- [x] Endpoint para detalhar um usuário.
  - [x] Requer client autenticado.
  - [x] Usuário com authority USER só pode buscar a si mesmo.
  - [x] Usuário com authority ADMIN pode buscar qualquer usuário.

- [ ] Funcionalidade para enviar o email de confirmação de conta na aplicação notification.
- [ ] Funcionalidade para enviar o SMS de confirmação de conta na aplicação notification.

## FIXME
- [x] Ajustar a funcionalidade de busca paginada de usuário. 

  Problema: Ao buscar os usuários, o Hibernate está buscando as Authorities de todos os usuários encontrados, 
e esses dados não são necessários para essa busca.

- [x] Ajustar a funcionalidade de busca de usuário por id.

  Problema: Ao buscar usuário, o Hibernate está buscando as Authorities do usuário, 
os quais não são necessários para a funcionalidade.

- [ ] Handler para UnauthorizedUserException