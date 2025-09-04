# API Documentation - Sistema de Gestão Escolar

Esta documentação descreve os endpoints da API REST do Sistema de Gestão de Atividades Extracurriculares da Mergington High School.

## Base URL

```
http://localhost:8080
```

## Formato de Resposta

Todas as respostas da API seguem o formato JSON. Em caso de erro, a resposta incluirá um campo `detail` com a descrição do erro.

**Exemplo de resposta de sucesso:**
```json
{
  "message": "Operação realizada com sucesso"
}
```

**Exemplo de resposta de erro:**
```json
{
  "detail": "Descrição do erro"
}
```

## Códigos de Status HTTP

| Código | Descrição |
|--------|-----------|
| `200` | Sucesso |
| `400` | Erro de validação ou requisição inválida |
| `401` | Falha de autenticação |
| `404` | Recurso não encontrado |
| `500` | Erro interno do servidor |

---

## Endpoints de Atividades

### Listar Todas as Atividades

Obtém uma lista de todas as atividades disponíveis, com opções de filtro.

```http
GET /activities
```

#### Parâmetros de Query (Opcionais)

| Parâmetro | Tipo | Descrição | Exemplo |
|-----------|------|-----------|---------|
| `day` | String | Filtrar por dia da semana | `Monday`, `Tuesday`, etc. |
| `start_time` | String | Horário mínimo de início (HH:mm) | `15:00` |
| `end_time` | String | Horário máximo de término (HH:mm) | `17:00` |

#### Exemplo de Requisição

```http
GET /activities?day=Monday&start_time=15:00&end_time=17:00
```

#### Exemplo de Resposta

```json
{
  "Chess Club": {
    "name": "Chess Club",
    "description": "Desenvolva suas habilidades estratégicas e táticas...",
    "schedule": "Segundas e quartas-feiras, 15:30 - 17:00",
    "scheduleDetails": {
      "days": ["Monday", "Wednesday"],
      "startTime": "15:30",
      "endTime": "17:00"
    },
    "maxCapacity": 20,
    "currentCapacity": 15,
    "participants": [
      "alice@mergington.edu",
      "bob@mergington.edu"
    ],
    "activityType": "ACADEMIC"
  },
  "Manga Maniacs": {
    "name": "Manga Maniacs",
    "description": "Mergulhe no universo épico dos mangás japoneses!...",
    "schedule": "Terças-feiras, 19:00 - 20:30",
    "scheduleDetails": {
      "days": ["Tuesday"],
      "startTime": "19:00",
      "endTime": "20:30"
    },
    "maxCapacity": 15,
    "currentCapacity": 8,
    "participants": [
      "charlie@mergington.edu",
      "diana@mergington.edu"
    ],
    "activityType": "ARTS"
  }
}
```

### Listar Dias Disponíveis

Obtém uma lista dos dias da semana que possuem atividades agendadas.

```http
GET /activities/days
```

#### Exemplo de Resposta

```json
[
  "Monday",
  "Tuesday", 
  "Wednesday",
  "Thursday",
  "Friday"
]
```

---

## Endpoints de Inscrições

### Inscrever Estudante em Atividade

Inscreve um estudante em uma atividade específica. Requer autenticação de professor.

```http
POST /activities/{activityName}/signup
Content-Type: application/x-www-form-urlencoded
```

#### Parâmetros da URL

| Parâmetro | Tipo | Descrição |
|-----------|------|-----------|
| `activityName` | String | Nome exato da atividade |

#### Parâmetros do Corpo

| Parâmetro | Tipo | Obrigatório | Descrição |
|-----------|------|-------------|-----------|
| `email` | String | Sim | Email do estudante |
| `teacher_username` | String | Sim | Username do professor autenticado |

#### Exemplo de Requisição

```http
POST /activities/Chess%20Club/signup
Content-Type: application/x-www-form-urlencoded

email=student@mergington.edu&teacher_username=teacher.chen
```

#### Exemplo de Resposta de Sucesso

```json
{
  "message": "Estudante inscrito com sucesso na atividade Chess Club"
}
```

#### Possíveis Erros

```json
// Autenticação necessária
{
  "detail": "Authentication required for this action"
}

// Credenciais inválidas  
{
  "detail": "Invalid teacher credentials"
}

// Atividade não encontrada
{
  "detail": "Activity not found: Invalid Activity"
}

// Capacidade máxima atingida
{
  "detail": "Activity has reached maximum capacity"
}

// Estudante já inscrito
{
  "detail": "Student already registered for this activity"
}
```

### Cancelar Inscrição de Estudante

Remove a inscrição de um estudante de uma atividade. Requer autenticação de professor.

```http
POST /activities/{activityName}/unregister
Content-Type: application/x-www-form-urlencoded
```

#### Parâmetros

Mesmos parâmetros do endpoint de inscrição.

#### Exemplo de Requisição

```http
POST /activities/Chess%20Club/unregister
Content-Type: application/x-www-form-urlencoded

email=student@mergington.edu&teacher_username=teacher.chen
```

#### Exemplo de Resposta de Sucesso

```json
{
  "message": "Inscrição cancelada com sucesso para a atividade Chess Club"
}
```

---

## Endpoints de Autenticação

### Login de Professor

Autentica um professor no sistema.

```http
POST /auth/login
Content-Type: application/x-www-form-urlencoded
```

#### Parâmetros do Corpo

| Parâmetro | Tipo | Obrigatório | Descrição |
|-----------|------|-------------|-----------|
| `username` | String | Sim | Username do professor |
| `password` | String | Sim | Senha do professor |

#### Exemplo de Requisição

```http
POST /auth/login
Content-Type: application/x-www-form-urlencoded

username=teacher.rodriguez&password=securePassword123
```

#### Exemplo de Resposta de Sucesso

```json
{
  "username": "teacher.rodriguez",
  "displayName": "Professor Rodriguez",
  "role": "TEACHER"
}
```

#### Exemplo de Resposta de Erro

```json
{
  "detail": "Invalid username or password"
}
```

### Verificar Sessão

Verifica se um professor está logado e retorna suas informações.

```http
GET /auth/check-session?username={username}
```

#### Parâmetros de Query

| Parâmetro | Tipo | Obrigatório | Descrição |
|-----------|------|-------------|-----------|
| `username` | String | Sim | Username do professor |

#### Exemplo de Requisição

```http
GET /auth/check-session?username=teacher.chen
```

#### Exemplo de Resposta de Sucesso

```json
{
  "username": "teacher.chen",
  "displayName": "Professor Chen",
  "role": "TEACHER"
}
```

#### Exemplo de Resposta de Erro

```json
{
  "detail": "Teacher not found"
}
```

---

## Tipos de Atividade (ActivityType)

O sistema categoriza automaticamente as atividades usando o enum `ActivityType` baseado em palavras-chave:

| Tipo | Label | Palavras-chave (PT/EN) | Cor |
|------|-------|------------------------|-----|
| `SPORTS` | Esportes | futebol, basquete, esporte, team, athletic | Verde |
| `ARTS` | Artes | arte, música, teatro, drama, manga, creative | Roxo |
| `ACADEMIC` | Acadêmico | ciência, matemática, estudo, olimpíada, learning | Azul |
| `COMMUNITY` | Comunidade | voluntário, comunidade, serviço, volunteer | Laranja |
| `TECHNOLOGY` | Tecnologia | computador, programação, robótica, coding | Índigo |

---

## Professores Padrão

O sistema vem pré-configurado com os seguintes professores:

| Username | Display Name | Role | Atividades Sugeridas |
|----------|--------------|------|---------------------|
| `admin` | Administrator | ADMIN | Todas |
| `teacher.rodriguez` | Professor Rodriguez | TEACHER | Art Club |
| `teacher.chen` | Professor Chen | TEACHER | Chess Club |

---

## Exemplos de Uso Completos

### Fluxo de Inscrição Completo

1. **Login do professor:**
```http
POST /auth/login
Content-Type: application/x-www-form-urlencoded

username=teacher.chen&password=defaultPassword
```

2. **Listar atividades disponíveis:**
```http
GET /activities
```

3. **Inscrever estudante:**
```http
POST /activities/Chess%20Club/signup
Content-Type: application/x-www-form-urlencoded

email=newstudent@mergington.edu&teacher_username=teacher.chen
```

### Filtrar Atividades por Horário

Para encontrar atividades no período da tarde (após 15:00):

```http
GET /activities?start_time=15:00
```

Para atividades apenas nas segundas-feiras:

```http
GET /activities?day=Monday
```

---

## Notas Técnicas

- **Encoding de URL**: Nomes de atividades com espaços devem ser codificados (ex: `Chess%20Club`)
- **Content-Type**: Endpoints POST usam `application/x-www-form-urlencoded`
- **CORS**: Configurado para aceitar requests de qualquer origem (`*`) em desenvolvimento
- **Validação**: Emails são validados automaticamente pelo value object `Email`
- **Capacidade**: O sistema previne inscrições quando a capacidade máxima é atingida
- **Duplicatas**: Não permite inscrições duplicadas do mesmo estudante na mesma atividade

---

## Interface Web

Além da API REST, o sistema inclui uma interface web acessível em:

```
http://localhost:8080/
```

A interface oferece:
- Visualização de atividades com filtros dinâmicos
- Modal de login para professores
- Sistema de inscrição/cancelamento via interface
- Categorização visual por cores
- Design responsivo

---

*Para mais informações sobre a arquitetura e implementação, consulte [README.md](README.md).*