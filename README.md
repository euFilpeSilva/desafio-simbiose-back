# Desafio Simbiose Back-End (Spring Boot)


Bem-vindo ao repositório do Desafio Simbiose Back-End! Este projeto, desenvolvido por mim, é parte do processo de seleção para a Simbiose. Neste repositório, você encontrará o código fonte e a documentação relacionados ao back-end do desafio.

O desafio consiste em criar uma aplicação back-end usando o framework Spring Boot, permitindo a gestão de pessoas em um sitema, temos o chamado CRUD(GET, POST, PUT, DELETE) . A aplicação utiliza MongoDB como banco de dados e inclui testes unitários com JUnit e Mockito, bem como o uso do MapStruct para conversões de objetos.

### Tecnologias Utilizadas
- Spring Boot: 4.0.0
- MongoDB: atual
- JUnit: 3.12.4
- Mockito: 3.12.4
- MapStruct: 1.5.3.Final
Outras bibliotecas ou tecnologias listadas no pom.xml

### Instalação e Execução
- Siga as instruções abaixo para configurar e executar o projeto localmente:

#### Clone o repositório: 
- Comece clonando este repositório para o seu ambiente local utilizando o seguinte comando:
  git clone https://github.com/euFilpeSilva/desafio-simbiose-back.git

  #### Configuração do Banco de Dados:
  - configure a conexão no arquivo application.properties:
   -  spring.data.mongodb.uri=sua_string_de_conexão
   -  spring.data.mongodb.database=nome_database

#### Execução do Projeto: Navegue até o diretório do projeto e execute-o usando o Maven: 
  - cd desafio-simbiose-back
- mvn spring-boot:run

O servidor estará em execução em http://localhost:8080 por padrão.

- Uso
A aplicação back-end disponibiliza uma API que permite buscar, atualizar, cadastrar e deletar pessoas da base de dados.


#### Endpoints:

- Exemplos de ferramentas que podem ser utilizadas pra testar a API: Postman ou Insomnia.

- Registro: [POST] http://localhost:8080/pessoa

   ![image](https://github.com/euFilpeSilva/desafio-simbiose-back/assets/79103757/8ebddf3f-a064-4648-9aef-3a7d2d2bbe4d)

- Atualizar: [PUT] http://localhost:8080/pessoa

  ![image](https://github.com/euFilpeSilva/desafio-simbiose-back/assets/79103757/d2ef5d09-866a-4d52-84ec-45acb0f7a454)


- Listar: [GET] http://localhost:8080/pessoa?page=0&size=3
- Listar por id: [GET] http://localhost:8080/pessoa/64eba46271bebc6b7a36573a
- Deletar: [DELETE] http://localhost:8080/pessoa/64eba46271bebc6b7a36573a

#### Estrutura do Projeto
- Usei como base o padrão MVC, sempre separando bem as responsabilidades de cada camada, garantindo assim um baixo acoplamento e facil entendimento da estrutura como um todo.

![image](https://github.com/euFilpeSilva/desafio-simbiose-back/assets/79103757/d34662a8-076d-43f0-ae4c-14db52a6b416)


#### Testes
Implementei os testes de unidade, onde podemos garantir que cada método, tanto da camada de controller quanto de serviço, estão recebendo e retornando o que se deve.

