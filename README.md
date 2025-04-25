# 🚗 Sistema de Gerenciamento de Vagas de Estacionamento

Aplicação desktop desenvolvida em **JavaFX** com integração ao **MySQL**, voltada para o controle de vagas em tempo real.

O sistema permite:
- Registrar novas vagas
- Ocupar e liberar vagas
- Armazenar placa do veículo e horário de entrada

Utiliza:
- Arquitetura **MVC**
- **Maven** para gerenciamento de dependências
- **Enums** e **LocalDateTime** para integridade dos dados

---

## 💻 Tecnologias Utilizadas
- Java 17+
- JavaFX
- MySQL
- Maven

---

## 📂 Estrutura do Projeto

src/ ├── controller/ ├── dao/ ├── database/ ├── model/ └── view/
---

## 🌍 English Version

### Parking Slot Management System

A JavaFX desktop application integrated with MySQL, designed to manage parking slots in real-time.

The system allows:
- Registering parking slots
- Occupying and releasing spots
- Storing license plates and entry times

Built with:
- **MVC architecture**
- **Maven** for dependency management
- Clean code using **Enums** and **LocalDateTime** for better data integrity

---

## 🛠️ Setup

1. Clone o repositório
2. Configure o banco de dados MySQL
3. Rode com Maven:
```bash
mvn clean javafx:run
