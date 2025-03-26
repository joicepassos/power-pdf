# Serviço Full Stack para Merge de PDFs Assíncrono - POWERPDF 

[![licence mit](https://img.shields.io/badge/licence-MIT-blue.svg)](./LICENSE)

Foi desenvolvido um serviço full stack para realizar o merge de arquivos PDF de forma assíncrona, utilizando Next.js e React no front-end, e três microserviços para gerenciar a lógica de negócios e a persistência de dados. Os microserviços criados são: **files-service**, **file-processor-service**, e **storage-service**.

## Descrição do Sistema

### Arquitetura
- **Front-end**: O front-end foi desenvolvido com **Next.js** e **React**, proporcionando uma interface intuitiva para o usuário realizar o upload dos PDFs que precisam ser mesclados. A interação com o back-end é feita por meio de chamadas API RESTful.
- **Microserviços**:
  - **files-service**: Responsável pelo gerenciamento dos arquivos, como receber as requisições e criar a fila de merges e armazenamento dos dados para os relatórios.
  - **file-processor-service**: Serviço que realiza o processamento dos arquivos PDF, incluindo a fusão dos documentos. Esse serviço opera de forma assíncrona, permitindo que os usuários continuem suas atividades enquanto o processo ocorre em segundo plano.
  - **storage-service**: Responsável pelo armazenamento dos arquivos. Após o processamento, os arquivos mesclados são armazenados de forma eficiente, prontos para serem baixados pelos usuários.

### Funcionalidades Principais
- **Upload de Arquivos PDF**: O usuário envia arquivos PDF através da interface do front-end, que são então gerenciados pelo **files-service**.
- **Merge Assíncrono de PDFs**: O **file-processor-service** gerencia o merge dos arquivos PDF de forma assíncrona, garantindo que o usuário não precise esperar diretamente pelo processo.
- **Armazenamento e Download**: Após o processamento, o **storage-service** armazena os PDFs mesclados, permitindo que o usuário faça o download quando o processo estiver concluído.

### Detalhes de Implementação
- **Tecnologias**:
  - **Next.js e React**: Usados para criar uma interface de usuário responsiva e dinâmica.
  - **Java 21 com Spring Boot**: Para o desenvolvimento dos microserviços, com foco na escalabilidade e performance.
  - **Banco de Dados**: Utilização de um banco de dados relacional ou NoSQL, conforme a necessidade de armazenamento temporário e persistente dos arquivos.
- **Processamento Assíncrono**: O merge dos arquivos PDF é feito de forma assíncrona para não bloquear a experiência do usuário. Os serviços de back-end utilizam filas ou outro mecanismo para gerenciar as tarefas de processamento.
- **Escalabilidade**: O sistema foi projetado para ser escalável, podendo suportar uma grande quantidade de usuários e arquivos simultâneos.

### Fluxo de Trabalho
1. O **files-service** recebe os arquivos PDF enviados pelos usuários.
2. O **file-processor-service** recebe a tarefa de merge via fila assíncrona, realiza o processamento dos arquivos e envia o resultado para o **storage-service**.
3. O **storage-service** armazena os PDFs mesclados e gera um link para download.
4. O usuário é notificado e pode realizar o download do PDF resultante.

### Como Executar

- Clonar repositório git:
```
git clone https://github.com/joicepassos/desafio-java
```
- Abrir o terminal e executar o Docker:
```
docker-compose up --build
```
- Acessar a documentação via Swagger e visualização das api em:
```
 http://localhost:8080/files/swagger-ui/index.html#/
```

```
 http://localhost:8081/file-processor/swagger-ui/index.html#/
```

```
 http://localhost:8082/storage/swagger-ui/index.html#/
```
- Digitar as credenciais de acesso:
  
```
user: admin
password: admin
```

- Acessar visualização do frontend:

```
 http://localhost:3000
```

Casoo já tenha feito o build para rodar aplicação novamente, digite:
```
docker-compose up
```


