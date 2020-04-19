# password-validator
Serviço de validação de passwords - ITI

## Construindo e rodando
Construindo e rodando o verificador de passwords

### Construindo e rodando a aplicação localmente
Para construir um jar executável, basta executar o seguinte comando:

$ ./gradlew bootJar
O **jar** executável ficará localizado no diretório __build/libs__ e é possível rodá-lo com o seguinte comando:

> **$ java -jar build/libs/gradle-spring-boot-project.jar**

Outra maneira de rodar a aplicação é executando o seguinte comando:

> **$ ./gradlew bootRun**

### Construindo e rodando via Docker
Nesse método, é necessário ter o Docker instalado localmente.  
Para construir a imagem, é necessario rodar o seguinte comando, na raiz do projeto:  
> **$ sudo docker build -t password-validator .**

Para rodar, no mesmo diretório, executar o seguinte comando:  
> **$ sudo docker run -it -p 8080:8080 password-validator**  

Nesse momento, podemos acessar a interface [Swaggger](http://localhost:8080/swagger-ui.html)

## Decisões de projeto
A seguir, é apresentado algumas decisões de projeto e o motivos pelos quais elas foram tomadas

### Problema
Para a resolução do problema, optei por utilizar regex do próprio java. Nesse ponto, utilizei a construção **\p{Punct}** para fazer o match no caracteres especiais **!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~**  
Também deixei a regex em uma property (**password.validator.regex.pattern**) para uma possível alteração da regra de validação.

### API
#### Versionamento
Optei por um versionamento via URL, diretamente no Controller, por conveniência. Não acredito ser a melhor abordagem. Em produção, acredito que esse versionamento poderia estar fora da modelagem e a URL poderia ser construída conforme a release do projeto, utilizando [Semantic Versioning](https://semver.org/), onde o primeiro dígito do versionamento seria concatenado na URL. Como trata-se de um teste e essa tarefa envolve um ambiente diferenciado e trabalho de DEVOPS, optei pelo método mais simples.
#### Utilização de Swagger
Optei pela utilização do Swagger utlizando a lib do springfox, porém acredito que poderia ser migrado para utilizar [Open API](https://www.openapis.org/), e não o fiz pelo tempo disponível

#### Modelo utilizado
Foi utilizado Webflux nas API, rodando sobre Netty, porém sem utilizar o modelo funcional. A opção por não utlização do modelo funcional utilizando RouterFuncions, por exemplo, não envolveu questões técnicas.

### Separação de camadas
Optei pela separação entre as camadas de transporte e de serviço, criando DTOs e objetos de domínio. O problema é simples e talvez não fosse necessário, mas decidi fazer para deixar claro o conceito e a separação entre as camadas, utilizando conversão explícita entre elas.  
Acho importante essa separação para que a interface de comunicação não interfera no desenvolvimento do **negócio**, deixa o domínio mais coeso e sem acoplamento com a tecnologia ou modelo utilizado (MVC, Webflux funcional ou não, gRPC e etc)

### Monitoramento
Optei por utilizar [Micrometer](https://micrometer.io/) + Actuator, em conjuto com [JMX](https://micrometer.io/docs/registry/jmx). Optei por esse modelo simplificado pela facilidade e por se tratar de um teste. Porém o código abstrai a tecnologia e um outro serviço mais robusto (Dynatrace, Influx e etc.) poderia facilmente ser plugado.

### Log tracing
Como o programa roda standalone, esse tipo de tecnologia não se faz necessária, porém imaginando que ele estaria inserido em um contexto de micro-serviços, com possivelmente algum agregador, inclui também o [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth) na stack do projeto.

### Testes
Na parte de testes, tentei cobrir todos os casos, mantendo praticamente 100% de cobertura, incluíndo além de testes unitários, testes de integração e testes de API.

### Tecnologias não utilizadas
Tecnologias e padrões arquiteturais como API Gateawy, implentação de segurança, criação de um BFF, utilização de banco de dados, I18N não foram adotados pela simplicidade do testes também pelo tempo disponível.
