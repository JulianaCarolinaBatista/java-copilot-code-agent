# Documentação - Sistema de Gestão Escolar

Este diretório contém toda a documentação do Sistema de Gestão de Atividades Extracurriculares da Mergington High School.

## 📚 Índice da Documentação

### Documentação Principal
- **[README.md](README.md)** - Documentação completa do sistema, arquitetura e guia de desenvolvimento
- **[API.md](API.md)** - Documentação detalhada da API REST com exemplos e códigos de erro

### Diagramas de Arquitetura
Localização: `docs/architecture/`

- **[system-context.puml](architecture/system-context.puml)** - Diagrama de contexto do sistema
- **[container-diagram.puml](architecture/container-diagram.puml)** - Diagrama de containers
- **[component-diagram.puml](architecture/component-diagram.puml)** - Diagrama de componentes
- **[clean-architecture.puml](architecture/clean-architecture.puml)** - Diagrama da arquitetura limpa

## 🔧 Visualizando os Diagramas

Os diagramas utilizam PlantUML com a extensão C4. Para visualizá-los:

### Opção 1: VS Code
1. Instale a extensão "PlantUML" no VS Code
2. Abra qualquer arquivo `.puml`
3. Use `Ctrl+Shift+P` → "PlantUML: Preview Current Diagram"

### Opção 2: Online
1. Acesse [plantuml.com/plantuml/uml](http://www.plantuml.com/plantuml/uml)
2. Cole o conteúdo do arquivo `.puml`
3. Visualize o diagrama gerado

### Opção 3: Linha de Comando
```bash
# Instalar PlantUML
sudo apt-get install plantuml

# Gerar PNG
plantuml docs/architecture/*.puml
```

## 📖 Guia de Navegação

### Para Desenvolvedores
1. Comece com [README.md](README.md) para entender a arquitetura geral
2. Consulte [API.md](API.md) para detalhes dos endpoints
3. Examine os diagramas em `architecture/` para visualizar a estrutura

### Para Usuários da API
1. Vá direto para [API.md](API.md)
2. Use os exemplos de requisição para testar
3. Consulte os códigos de erro para debugging

### Para Administradores de Sistema
1. Leia a seção "Configuração e Execução" no [README.md](README.md)
2. Consulte as informações sobre migrações e dados iniciais
3. Veja as configurações de segurança e monitoramento

## 🚀 Links Rápidos

- **Aplicação Local**: http://localhost:8080
- **API REST**: http://localhost:8080/activities
- **Health Check**: http://localhost:8080/actuator/health
- **Repositório**: https://github.com/JulianaCarolinaBatista/java-copilot-code-agent

## 📝 Última Atualização

Esta documentação foi atualizada para refletir:
- ✅ ActivityType enum com categorização automática
- ✅ Migração V002 (Manga Maniacs Club)
- ✅ AuthController para autenticação
- ✅ API REST completa com todos os endpoints
- ✅ Estrutura Clean Architecture atualizada
- ✅ Configurações de segurança e desenvolvimento

---

*Para reportar problemas na documentação ou sugerir melhorias, abra uma issue no repositório.*