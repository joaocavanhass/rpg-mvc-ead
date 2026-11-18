# ⚔️ Arena RPG - Batalha em Turnos (MVC & Design Patterns)

Projeto desenvolvido para a **Avaliação A3** da disciplina de **Engenharia e Arquitetura de Software** (Centro Universitário Senac). A aplicação consiste em um jogo de combate RPG por turnos via console, estruturado sobre a arquitetura **MVC** e aplicando quatro padrões de projeto (GoF) distintos.

---

## 🛠️ Tecnologias e Requisitos
* **Linguagem:** Java (JDK 17 ou superior — desenvolvido e testado em Java 25)
* **Paradigma:** Orientação a Objetos (Encapsulamento, Herança, Polimorfismo, Interfaces)
* **Arquitetura:** MVC (Model-View-Controller)
* **Dependências:** Nenhuma biblioteca externa necessária (Java SE puro)

---

## 🎮 Funcionalidades Implementadas
* **Criação de Herói**: Cadastro do nome do jogador (com validação de campo obrigatório) e seleção entre três classes distintas (Guerreiro, Mago e Arqueiro).

* **Seleção de Oponente**: Três níveis de dificuldade com inimigos de atributos escalonados (Goblin Saqueador, Cavaleiro Sombrio e Dragão Ancião).

* **Sistema de Combate em Turnos**: Escolha dinâmica entre três estratégias de ataque (Ataque Básico, Golpe Pesado e Disparo Arcano) ou uso de Poção de Cura (limite de 2 por partida).

* **Validação e Proteção de Estado**: Tratamento de entradas inválidas no console e bloqueio de operações incompatíveis com o momento da partida (ex.: tentar reiniciar uma batalha em andamento ou atacar após o término).

## 🚀 Como Compilar e Executar

### Pelo Terminal (Linha de Comando)
1. Clone o repositório e acesse a pasta raiz:
   ```bash
   git clone https://github.com/joaocavanhass/rpg-mvc-ead.git
   cd rpg-mvc-ead