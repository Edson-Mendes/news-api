# Lista do que ainda planejo fazer nesse projeto.

## TODO
- [ ] Endpoint para detalhar um usuário.
  - [ ] Requer client autenticado.
  - [ ] Usuário com authority USER só pode buscar a si mesmo.
  - [ ] Usuário com authority ADMIN pode buscar qualquer usuário.

- [ ] Funcionalidade para enviar o email de confirmação de conta na aplicação notification.
- [ ] Funcionalidade para enviar o SMS de confirmação de conta na aplicação notification.

## FIXME
- [ ] Ajustar a funcionalidade de busca paginada de usuário. 
Problema: Ao buscar os usuários, o Hibernate está buscando as Authorities de todos os usuários encontrados, 
e esses dados não são necessários para essa busca.