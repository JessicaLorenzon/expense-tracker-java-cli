# Expense Tracker

Projeto feito seguindo o roadmap de projetos do [roadmap.sh](https://roadmap.sh/projects/expense-tracker), com o
objetivo de praticar Java e aplicar conceitos de persistência, organização de dados e operações básicas de entrada e
saída.

## Descrição

O Expense Tracker é uma aplicação de linha de comando (CLI) para controle de despesas.  
Ele permite que você adicione, liste, atualize e remova gastos.

É uma ferramenta simples para gerenciar finanças pessoais diretamente pelo terminal.

## Tecnologias utilizadas

- **Java** – Linguagem principal
- [**Gson**](https://github.com/google/gson) – Biblioteca para serialização e desserialização de objetos JSON
- **CLI (Command Line Interface)** – Interface baseada em terminal
- **Arquivo `.jar`** – Distribuição empacotada da aplicação

## Como rodar o projeto

### 1. Baixe ou clone o repositório

```bash
git clone https://github.com/JessicaLorenzon/expense-tracker-java-cli.git
cd expense-tracker-java-cli
```

### 2. Navegue até o diretório onde está o `.jar`:

```bash
cd out/artifacts/expense_tracker_java_cli_jar
```

### 3. Execute o aplicativo:

```bash
java -jar expense-tracker-java-cli.jar
```

## Exemplo de uso:

```bash
$ expense-tracker-java-cli add --description "Lunch" --amount 20
# Expense added successfully (ID: 1)

$ expense-tracker-java-cli add --description "Dinner" --amount 10
# Expense added successfully (ID: 2)

$ expense-tracker-java-cli list
# ID  Date       Description  Amount
# 1   2025-07-03  Lunch        $20
# 2   2025-07-03  Dinner       $10

$ expense-tracker-java-cli summary
# Total expenses: $30

$ expense-tracker-java-cli delete --id 2
# Expense with id 2 deleted successfully

$ expense-tracker-java-cli update --id 1 --description "Books" --amount 40
# Expense with id 1 updated successfully

$ expense-tracker-java-cli summary --month 8
# Total expenses for August: $40
```