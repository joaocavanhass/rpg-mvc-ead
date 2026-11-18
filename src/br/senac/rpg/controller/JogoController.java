package br.senac.rpg.controller;

import br.senac.rpg.model.Batalha;
import br.senac.rpg.model.entity.Personagem;
import br.senac.rpg.model.factory.PersonagemFactory;
import br.senac.rpg.model.factory.TipoClasse;
import br.senac.rpg.model.state.EstadoFinalizado;
import br.senac.rpg.model.strategy.AtaqueBasicoStrategy;
import br.senac.rpg.model.strategy.AtaqueMagicoStrategy;
import br.senac.rpg.model.strategy.AtaquePesadoStrategy;
import br.senac.rpg.view.ConsoleView;

public class JogoController {
    private final ConsoleView view;

    public JogoController(ConsoleView view) {
        this.view = view;
    }

    public void iniciarAplicacao() {
        view.exibirBoasVindas();
        boolean executando = true;

        while (executando) {
            view.exibirMenuPrincipal();
            int opcao = view.lerInteiro("Digite sua opção: ");

            switch (opcao) {
                case 1 -> configurarEIniciarBatalha();
                case 2 -> view.exibirRegras();
                case 0 -> {
                    view.exibirMensagem("Encerrando Arena RPG. Até a próxima!");
                    executando = false;
                }
                default -> view.exibirErro("Opção inválida! Escolha 1, 2 ou 0.");
            }
        }
    }

    private void configurarEIniciarBatalha() {
        // 1. Validação do Nome do Herói
        String nomeHeroi;
        while (true) {
            nomeHeroi = view.lerTexto("\nDigite o nome do seu Herói: ").trim();
            if (!nomeHeroi.isEmpty()) {
                break;
            }
            view.exibirErro("O nome do herói é obrigatório!");
        }

        // 2. Seleção de Classe (Factory Method)
        TipoClasse classeEscolhida = null;
        while (classeEscolhida == null) {
            view.exibirMenuClasses();
            int opClasse = view.lerInteiro("Escolha a classe (1-3): ");
            classeEscolhida = switch (opClasse) {
                case 1 -> TipoClasse.GUERREIRO;
                case 2 -> TipoClasse.MAGO;
                case 3 -> TipoClasse.ARQUEIRO;
                default -> {
                    view.exibirErro("Classe inválida! Digite 1, 2 ou 3.");
                    yield null;
                }
            };
        }

        // 3. Seleção de Dificuldade / Inimigo (Factory Method)
        int nivelInimigo = 0;
        while (nivelInimigo < 1 || nivelInimigo > 3) {
            view.exibirMenuDificuldade();
            int opNivel = view.lerInteiro("Escolha o nível do oponente (1-3): ");
            if (opNivel >= 1 && opNivel <= 3) {
                nivelInimigo = opNivel;
            } else {
                view.exibirErro("Nível inválido! Escolha entre 1 e 3.");
            }
        }

        Personagem heroi = PersonagemFactory.criarHeroi(classeEscolhida, nomeHeroi);
        Personagem inimigo = PersonagemFactory.criarInimigoPorNivel(nivelInimigo);

        // 4. Instanciação da Batalha e registro do Observer
        Batalha batalha = new Batalha(heroi, inimigo);
        batalha.adicionarObserver(view);

        // Inicia a batalha (transição de EstadoPreparacao -> EstadoEmAndamento)
        batalha.iniciarBatalha();

        // 5. Loop de Turnos controlado pelo Padrão State
        while (!(batalha.getEstadoAtual() instanceof EstadoFinalizado)) {
            view.exibirMenuAcoes(batalha.getPocoesRestantes());
            int acao = view.lerInteiro("Ação: ");

            try {
                switch (acao) {
                    case 1 -> batalha.realizarAtaque(new AtaqueBasicoStrategy());
                    case 2 -> batalha.realizarAtaque(new AtaquePesadoStrategy());
                    case 3 -> batalha.realizarAtaque(new AtaqueMagicoStrategy());
                    case 4 -> batalha.usarPocaoCura();
                    case 5 -> batalha.iniciarBatalha(); // Demonstra bloqueio do State
                    default -> view.exibirErro("Ação inválida! Escolha entre 1 e 5.");
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                view.exibirErro(e.getMessage());
            }
        }

        // Demonstração extra: tentar atacar após o fim da partida é bloqueado pelo EstadoFinalizado
        try {
            batalha.realizarAtaque(new AtaqueBasicoStrategy());
        } catch (IllegalStateException e) {
            view.exibirMensagem("[Proteção de Estado]: " + e.getMessage());
        }
    }
}
