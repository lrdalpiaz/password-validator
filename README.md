# password-validator
Serviço de validação de passwords - ITI

##Construindo e rodando a aplicação localmente
Para construir um jar executável, basta executar o seguinte comando:

$ ./gradlew bootJar
O jar executável ficará localizado no diretório build/libs e é possível rodá-lo com o seguinte comando:

$ java -jar build/libs/gradle-spring-boot-project.jar

Outra maneira de rodar a aplicação é executando o seguinte comando:

$ ./gradlew bootRun

##Construindo e rodando via Docker

Nesse método, é necessário ter o Docker instalado localmente
Para construir a imagem, é necessario rodar o seguinte comando, na raiz do projeto:
$ sudo docker build -t password-validator .

Para rodar, no mesmo diretório, executar o seguinte comando:
$ sudo docker run -it -p 8080:8080 password-validator

Nesse momento, podemos acessar a interface Swaggger em http://localhost:8080/swagger-ui.html


##Decisões de projeto

