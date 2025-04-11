 🔰 Visão Geral

Este projeto é uma aplicação Spring Boot desenvolvida com o objetivo de gerenciar usuários e carros, incluindo funcionalidades de cadastro, listagem, atualização e remoção para ambos os domínios.

Além disso, o sistema possui um mecanismo de autenticação básica com `BCrypt`, aplicada ao endpoint de cadastro de carros, oferecendo uma camada adicional de segurança.


 📁 Estrutura de Pacotes

- `user`: Contém as classes relacionadas à entidade usuário (modelo, repositório, controller).
- `car`: Contém as classes relacionadas à entidade carro (modelo, repositório, controller).
- `filter`: Contém o filtro de autenticação para proteger requisições sensíveis.


 👤 UserModel.java

Entidade JPA que representa a tabela de usuários no banco de dados.

 🧩 Atributos
- `UUID id`: Identificador único do usuário.
- `String name`: Nome do usuário.
- `String email`: Email do usuário (armazenado de forma criptografada).
- `String password`: Senha do usuário (criptografada).

 🧪 Anotações
- `@Entity(name = "tb_usuario")`: Define o nome da tabela no banco de dados.
- `@Id` e `@GeneratedValue(generator = "UUID")`: ID gerado automaticamente via UUID.


 📚 IUserRepository.java

Interface que estende `JpaRepository`, permitindo acesso ao banco de dados para a entidade `UserModel`.

 🔎 Métodos personalizados
- `UserModel findByname(String name)`: Busca um usuário pelo nome.


 🧑‍💼 UserController.java

Controlador REST responsável por gerenciar usuários.

 📥 Endpoints
- `POST /user/novo`: Cria um novo usuário, criptografando a senha e o email.
- `GET /user/usercadastrados`: Retorna todos os usuários cadastrados.
- `PUT /user/atualiza`: Atualiza os dados de um usuário.
- `DELETE /user/deleteuser/{id}`: Remove um usuário pelo ID.

 🔒 Segurança
- As senhas e os emails são armazenados de forma criptografada com `BCrypt`.


 🔐 FilterAuth.java

Filtro HTTP que intercepta requisições ao endpoint `/carro/novo`, aplicando autenticação básica HTTP.

 🛡️ Funcionamento
1. Verifica o cabeçalho `Authorization` (formato Basic Auth).
2. Decodifica o Base64 e separa nome de usuário e senha.
3. Busca o usuário no banco via `userRepository.findByname(...)`.
4. Valida a senha com `BCrypt.verifyer()`.
5. Se autenticado, permite a requisição e adiciona o ID do usuário na request.

 📌 Observação
- O filtro se aplica somente ao endpoint `POST /carro/novo`.
- Retorna `401 Unauthorized` em caso de falha.


 🚗 CarModel.java

Entidade JPA que representa os carros.

 🧩 Atributos
- `UUID idCarro`: ID único do carro (chave primária).
- `String nome`: Nome do carro.
- `String senha`: Senha de acesso do carro (criptografada).
- `UUID id`: Identificador do usuário relacionado (referência lógica).


 📚 ICarRepository.java

Interface que estende `JpaRepository` para a entidade `CarModel`.

 🔎 Métodos personalizados
- `CarModel findByNome(String nome)`: Retorna um carro pelo nome.


 🚘 CarController.java

Controlador REST responsável por gerenciar os carros.

 📥 Endpoints
- `POST /carro/novo`: Cadastra um novo carro (requer autenticação).
- `GET /carro/listar`: Lista todos os carros cadastrados.
- `PUT /carro/atualizar`: Atualiza os dados de um carro.
- `DELETE /carro/delete/{id}`: Deleta um carro pelo ID.

 🔒 Segurança
- A senha do carro é criptografada com `BCrypt`.
- Apenas usuários autenticados (via filtro) podem cadastrar carros.


 ✅ Considerações Finais

Este projeto é uma base sólida para sistemas com usuários e entidades associadas, utilizando:
- Spring Boot com Spring Web e Spring Data JPA.
- Autenticação básica e criptografia com `BCrypt`.
- Controle de acesso via filtros HTTP personalizados.

Pode ser facilmente expandido para suportar autenticação via tokens JWT, relacionamentos entre entidades e outros níveis de segurança e escalabilidade.
