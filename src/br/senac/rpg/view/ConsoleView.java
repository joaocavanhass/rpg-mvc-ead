package br.senac.rpg.view;

import br.senac.rpg.model.entity.Personagem;
import br.senac.rpg.model.observer.BatalhaObserver;
import java.util.Scanner;

public class ConsoleView implements BatalhaObserver {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirBoasVindas() {
        System.out.println("==========================================");
        System.out.println("       ⚔️  ARENA RPG - MVC & PATTERNS ⚔️    ");
        System.out.println("==========================================");
    }

    public String lerTexto(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                exibirErro("Entrada inválida! Digite apenas números inteiros.");
            }
        }
    }

    public void exibirMenuPrincipal() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Iniciar Nova Batalha");
        System.out.println("2. Regras e Padrões do Jogo");
        System.out.println("0. Sair");
    }

    public void exibirMenuClasses() {
        System.out.println("\n--- ESCOLHA SUA CLASSE ---");
        System.out.println("1. Guerreiro (HP: 140 | ATQ: 22 | DEF: 14) - Tanque resistente");
        System.out.println("2. Mago      (HP: 95  | ATQ: 32 | DEF: 6)  - Alto dano ofensivo");
        System.out.println("3. Arqueiro  (HP: 115 | ATQ: 27 | DEF: 10) - Equilibrado e ágil");
    }

    public void exibirMenuDificuldade() {
        System.out.println("\n--- ESCOLHA O OPONENTE ---");
        System.out.println("1. Nível 1 - Goblin Saqueador (Fácil)");
        System.out.println("2. Nível 2 - Cavaleiro Sombrio (Médio)");
        System.out.println("3. Nível 3 - Dragão Ancião (Difícil)");
    }

    public void exibirMenuAcoes(int pocoesRestantes) {
        System.out.println("\nEscolha sua ação neste turno:");
        System.out.println("1. Ataque Básico (Dano seguro)");
        System.out.println("2. Golpe Pesado (150% dano | 70% de chance de acerto)");
        System.out.println("3. Disparo Arcano (Ignora parte da defesa do inimigo)");
        System.out.println("4. Usar Poção de Cura (+35 HP | Restantes: " + pocoesRestantes + ")");
        System.out.println("5. Testar Ação Inválida (Tentar reiniciar batalha em andamento)");
    }

    public void exibirRegras() {
        System.out.println("\n--- REGRAS DA ARENA ---");
        System.out.println("• Crie seu herói e enfrente o oponente em combates por turnos.");
        System.out.println("• A cada turno, você escolhe uma estratégia de ataque ou usa uma poção.");
        System.out.println("• Vence quem zerar os pontos de vida (HP) do adversário primeiro.");
    }

    public void exibirMensagem(String msg) {
        System.out.println(msg);
    }

    public void exibirErro(String erro) {
        System.out.println("[AVISO]: " + erro);
    }

    // Implementação dos métodos do Padrão Observer
    @Override
    public void onEventoBatalha(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void onAtualizacaoStatus(Personagem heroi, Personagem inimigo, int turno) {
        System.out.println("\n------------------------------------------");
        System.out.println("STATUS ATUAL (Próximo Turno: " + turno + ")");
        System.out.println("HERÓI  : " + heroi);
        System.out.println("INIMIGO: " + inimigo);
        System.out.println("------------------------------------------");
    }

    @Override
    public void onBatalhaFinalizada(Personagem vencedor, Personagem perdedor) {
        System.out.println("\n==========================================");
        System.out.println("🏆 FIM DE JOGO!");
        System.out.println("VENCEDOR : " + vencedor.getNome() + " (" + vencedor.getClasse() + ")");
        System.out.println("DERROTADO: " + perdedor.getNome());
        System.out.println("==========================================");
    }
}