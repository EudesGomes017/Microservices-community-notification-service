# 🔔 Notification Service

Este microsserviço é responsável por escutar eventos Kafka produzidos pelo `center-service`, processá-los e emitir notificações (atualmente via logs) quando há alterações de lotação nos centros comunitários.

> 📦 Projeto parte do ecossistema **Microservices Community Centers**, que simula uma arquitetura distribuída com serviços independentes.
 ![Demonstração BackEnd](centerservice.gif)

---

## 🚀 Tecnologias Utilizadas

| Tecnologia         | Descrição                                     |
|--------------------|----------------------------------------------|
| Java 21            | Linguagem principal                          |
| Spring Boot 3      | Framework para desenvolvimento web           |
| Spring Kafka       | Integração com Apache Kafka                  |
| MongoDB            | Persistência (futura extensão para histórico de notificações) |
| Docker             | Containerização                              |
| SpringDoc Swagger  | Documentação automática da API               |

---

## 📂 Endpoints REST (Swagger)

Documentação disponível quando o serviço está rodando:

🧪 [http://localhost:8083/swagger-ui/index.html](http://localhost:8083/swagger-ui/index.html)

---

## 📦 Como rodar via Docker

### Pré-requisitos
- `docker` e `docker-compose` instalados
- Kafka, Zookeeper e MongoDB rodando (pelo `docker-compose` principal do projeto)

### Passos

1. **Clone este repositório:**
```bash
git clone https://github.com/seu-usuario/notification-service.git
cd notification-service
(Opcional) Adapte as variáveis no application-docker.properties

properties
spring.data.mongodb.uri=mongodb://mongo:27017/notification_service_db
spring.kafka.bootstrap-servers=kafka:9092
server.port=8083
server.address=0.0.0.0
Construa a imagem e suba o container:

Normalmente chamado a partir do docker-compose.yml no projeto pai (community-centers)

yaml
  notification-service:
    build:
      context: ../notification-service
      dockerfile: Dockerfile
    container_name: notification-service
    ports:
      - "8083:8083"
    environment:
      SPRING_PROFILES_ACTIVE: dev
    networks:
      - community-net
🧪 Como testar
Você pode acionar o serviço enviando eventos para o Kafka topic center-occupancy-alert.

Exemplo usando Postman via center-service:

http
POST http://localhost:8081/api/centers/create
Content-Type: application/json

{
  "name": "Centro Leste",
  "maxCapacity": 100,
  "currentOccupancy": 101
}
🧾 O notification-service vai logar algo como:

[ALERT] Centro Leste ultrapassou a capacidade máxima!
❗Dificuldades enfrentadas
❌ Problemas de conexão com o host kafka e mongo: foi necessário garantir que os nomes usados nos .properties correspondessem exatamente aos nomes dos containers no docker-compose.

❌ Encoding errado em arquivos .properties: erro MalformedInputException exigiu salvar arquivos em UTF-8.

❌ Swagger e Postman sem resposta: faltava server.address=0.0.0.0 e portas corretamente expostas.

🛠️ Melhorias Futuras
 Persistência dos logs de notificação no MongoDB

 Envio de e-mails reais usando SMTP

 Testes com Testcontainers + Kafka Embedded

 Autenticação e versionamento da API

👨‍💻 Autor
Desenvolvido por Eudes Gomes
