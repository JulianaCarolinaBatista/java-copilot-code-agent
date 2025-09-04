# Sistema de Gestão Escolar - Mergington High School

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen.svg)
![MongoDB](https://img.shields.io/badge/MongoDB-4.4+-green.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

Sistema moderno de gestão de atividades extracurriculares desenvolvido com **Clean Architecture**, Spring Boot e MongoDB. Permite que professores administrem atividades e estudantes se inscrevam através de uma interface web intuitiva.

## 🚀 Características Principais

- **Clean Architecture** com separação clara de responsabilidades
- **API REST** completa para gestão de atividades
- **Sistema de autenticação** para professores
- **Interface web responsiva** com JavaScript vanilla
- **Banco de dados MongoDB** com migrações automáticas
- **Testes abrangentes** com JUnit 5 e Testcontainers
- **Segurança robusta** com criptografia Argon2

## 📋 Funcionalidades

### 🎓 Gestão de Atividades
- Listagem de atividades com filtros avançados
- Categorização automática por tipo (Esportes, Artes, Acadêmico, Comunidade, Tecnologia)
- Horários flexíveis e capacidade máxima configurável

### 👨‍🏫 Sistema de Autenticação
- Login seguro para professores
- Sessões baseadas em username
- Controle de acesso para operações administrativas

### 📝 Inscrições de Estudantes
- Inscrição e cancelamento de estudantes em atividades
- Validações de capacidade e duplicatas
- Feedback em tempo real para ações

## 🛠️ Tecnologias

- **Backend**: Java 21, Spring Boot 3.5.4, Spring Security, Spring Data MongoDB
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)
- **Banco de Dados**: MongoDB com Mongock para migrações
- **Segurança**: BouncyCastle, Argon2 password encoding
- **Testes**: JUnit 5, Mockito, Testcontainers
- **Build**: Maven 3.8+

## 🔧 Configuração e Execução

### Pré-requisitos
- Java 21
- Maven 3.8+
- MongoDB 4.4+ (Docker recomendado)

### Início Rápido

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/JulianaCarolinaBatista/java-copilot-code-agent.git
   cd java-copilot-code-agent
   ```

2. **Inicie o MongoDB** (Docker):
   ```bash
   docker run -d -p 27017:27017 --name mongodb mongo:latest
   ```

3. **Configure as variáveis de ambiente** (opcional):
   ```bash
   cp .env.example .env
   # Edite .env com suas configurações
   ```

4. **Execute a aplicação**:
   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a aplicação**:
   - Interface Web: http://localhost:8080
   - API REST: http://localhost:8080/activities

### Comandos Úteis

```bash
# Build completo
mvn clean install

# Executar testes
mvn test

# Gerar relatório de cobertura
mvn jacoco:report

# Package sem testes
mvn package -DskipTests
```

## 🌐 API Endpoints

### Atividades
```http
GET    /activities                          # Listar todas as atividades
GET    /activities?day=Monday               # Filtrar por dia
GET    /activities?start_time=15:00         # Filtrar por horário
GET    /activities/days                     # Listar dias disponíveis
```

### Inscrições
```http
POST   /activities/{nome}/signup            # Inscrever estudante
POST   /activities/{nome}/unregister        # Cancelar inscrição
```

### Autenticação
```http
POST   /auth/login                          # Login de professor
GET    /auth/check-session                  # Verificar sessão
```

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture** com 4 camadas principais:

- **Domain**: Entidades, Value Objects e interfaces de repositório
- **Application**: Casos de uso e DTOs
- **Infrastructure**: Implementações de repositório, configurações e migrações
- **Presentation**: Controllers REST e mappers

Para mais detalhes, consulte a [documentação completa](docs/README.md).

## 🧪 Testes

O projeto possui cobertura abrangente de testes:

```bash
# Executar todos os testes
mvn test

# Testes específicos
mvn test -Dtest=ActivityTest
mvn test -Dtest=StudentRegistrationUseCaseTest

# Testes de integração com Testcontainers
mvn test -Dtest=MangaManiacsMigrationTest
```

## 📊 Atividades Disponíveis

O sistema vem pré-configurado com diversas atividades:

- **Chess Club** - Clube de xadrez (Segunda/Quarta 15:30-17:00)
- **Art Club** - Clube de artes (Terça/Quinta 15:30-17:00)  
- **Drama Club** - Clube de teatro (Quarta/Sexta 16:00-18:00)
- **Manga Maniacs** - Clube de mangás (Terça 19:00-20:30)
- **Soccer Team** - Time de futebol (Segunda/Quarta/Sexta 16:00-18:00)

## 👥 Usuários Padrão

- **admin** - Administrador principal
- **teacher.rodriguez** - Professor de artes  
- **teacher.chen** - Professor de xadrez

As senhas são configuráveis via variáveis de ambiente ou usam valores seguros padrão.

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

&copy; 2025 Mergington High School &bull; [Code of Conduct](https://www.contributor-covenant.org/version/2/1/code_of_conduct/code_of_conduct.md) &bull; [MIT License](https://gh.io/mit)

