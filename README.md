# API Payment - Spring Boot - Java 17

## Sobre a API
A **API de Payments** é uma aplicação que permite realizar transações de pagamentos.

### Funcionalidades
- Criar transações de pagamento.
- Consultar pagamentos com filtros.
- Atualizar status do Pagamento.
- Excluir pagamentos em processamento.


## Tecnologias Utilizadas
- Java 17
- Spring Boot
- JPA/Hibernate
- Maven
- H2 data base

## Get Request com filtros:
Request para busca transacoes com os filtros:

- debitCode
- cpfCnpj
- status

Alem de permitir a busca utilizando o filtro de data:

- startDate
- endDate

Devem ser utilizadas em conjunto obrigatoriamente seguindo o seguinte formato:

```bash
 2025-04-25T00:00:00
```

### Curl Get :
```bash
curl --request GET \
  --url 'http://localhost:8080/api/v1/payments?debitCode=450002&cpfCnpj=12345678901&status=Pendente%20de%20Processamento&startDate=2025-04-25T00%3A00%3A00&endDate=2025-04-27T23%3A59%3A59' \
  --header 'Accept: application/json'
```
## Post Request:

Request para registrar um novo payment. Segue o seguinte Json como input:

```bash
{
  "debitCode": 450001,
  "cpfCnpj": "12345678901",
  "paymentMethod": "cartao_debito",
  "card": "4111111111111111",
  "value": 100.50
}
```
### Curl Post Card Debit :
```bash
curl --request POST \
  --url http://localhost:8080/api/v1/payments \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.1' \
  --data '{
  "debitCode": 450005,
  "cpfCnpj": "12345678901",
  "paymentMethod": "cartao_debito",
  "card": "4111111111111111",
  "value": 100.50
}
'
```
### Curl Post Card Credit :
```bash
curl --request POST \
  --url http://localhost:8080/api/v1/payments \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.1' \
  --data '{
  "debitCode": 450006,
  "cpfCnpj": "12345678901",
  "paymentMethod": "cartao_credito",
  "card": "4111111111111111",
  "value": 100.50
}
'
```
### Curl Post Pix:
```bash
curl --request POST \
  --url http://localhost:8080/api/v1/payments \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.1' \
  --data '{
  "debitCode": 450002,
  "cpfCnpj": "12345678901",
  "paymentMethod": "PIX",
  "value": 145.99
}
'
```
### Curl Post Boleto:
```bash
curl --request POST \
  --url http://localhost:8080/api/v1/payments \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.1' \
  --data '{
  "debitCode": 450003,
  "cpfCnpj": "12345678901",
  "paymentMethod": "BOLETO",
  "value": 57.93
}
'
```

## Put Request:

Request para atualizacao de payments. Segue o seguinte Json como input:

```bash
{
	"paymentId": 1, 
	"status": "Processado com Sucesso"
}
```

### Curl Put Processado com Sucesso:
```bash
curl --request PUT \
  --url http://localhost:8080/api/v1/payments \
  --header 'Content-Type: application/json' \
  --data '{
	"paymentId": 1, 
	"status": "Processado com Sucesso"
}'
```
### Curl Put Processado com Falha:
```bash
curl --request PUT \
  --url http://localhost:8080/api/v1/payments \
  --header 'Content-Type: application/json' \
  --data '{
	"paymentId": 1, 
	"status": "Processado com Falha"
}'
```
### Curl Put Pendente de Processamento:
```bash
curl --request PUT \
  --url 'http://localhost:8080/api/v1/payments?=' \
  --header 'Content-Type: application/json' \
  --data '{
	"paymentId": 1, 
	"status": "Pendente de Processamento"
}'
```

## Delete Request:
Request para deleta payments em processamento com base no id.
Subustitua {id} pelo id desejado.

### Curl Delete:
```bash
curl --request DELETE \
  --url http://localhost:8080/api/v1/payments/{id}
```


## Como Subir a Aplicação
1. Clone o repositório:
   ```bash
   git clone https://github.com/RafaelMSantos19/apiTransaction.git
   ```
2. Entre no diretório do projeto:
   ```bash
   cd apiTransaction
   ```
3. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```



## Contato
- **Nome:** Rafael M Santos  
- **Email:** rafaelhmsantos19@gmail.com 
- **LinkedIn:** [LinkedIn](www.linkedin.com/in/rafael-santos-94a56a1b2)


