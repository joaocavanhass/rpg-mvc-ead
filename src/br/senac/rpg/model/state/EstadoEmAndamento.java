package br.senac.rpg.model.state;

import br.senac.rpg.model.Batalha;
import br.senac.rpg.model.entity.Personagem;
import br.senac.rpg.model.strategy.AtaqueBasicoStrategy;
import br.senac.rpg.model.strategy.AtaquePesadoStrategy;
import br.senac.rpg.model.strategy.EstrategiaAtaque;
import java.util.Random;

public class EstadoEmAndamento implements EstadoBatalha {
    private final Random random = new Random();

    @Override
    public void iniciar(Batalha batalha) {
        throw new IllegalStateException("A batalha já está em andamento.");
    }

    @Override
    public void executarTurnoAtaque(Batalha batalha, EstrategiaAtaque estrategia) {
        Personagem heroi = batalha.getHeroi();
        Personagem inimigo = batalha.getInimigo();

        batalha.notificarEvento("\n--- TURNO " + batalha.getTurno() + " ---");

        // 1. Ação do Herói usando Strategy
        heroi.setEstrategiaAtaque(estrategia);
        int danoHeroi = heroi.executarAtaque(inimigo);

        if (danoHeroi == 0) {
            batalha.notificarEvento(heroi.getNome() + " tentou usar " + estrategia.getNomeHabilidade() + ", mas ERROU o golpe!");
        } else {
            batalha.notificarEvento(heroi.getNome() + " usou " + estrategia.getNomeHabilidade()
                    + " e causou " + danoHeroi + " de dano em " + inimigo.getNome() + "!");
        }

        // Verifica se o inimigo foi derrotado
        if (!inimigo.estaVivo()) {
            finalizarComVencedor(batalha, heroi, inimigo);
            return;
        }

        // 2. Turno do Inimigo
        executarAcaoInimigo(batalha);
    }

    @Override
    public void executarTurnoCura(Batalha batalha) {
        if (batalha.getPocoesRestantes() <= 0) {
            throw new IllegalStateException("Você não possui mais poções de cura!");
        }

        Personagem heroi = batalha.getHeroi();
        batalha.notificarEvento("\n--- TURNO " + batalha.getTurno() + " ---");

        batalha.consumirPocao();
        int curado = heroi.curar(35);
        batalha.notificarEvento(heroi.getNome() + " usou uma Poção de Vida e recuperou " + curado
                + " HP! (Poções restantes: " + batalha.getPocoesRestantes() + ")");

        // Inimigo ainda ataca neste turno
        executarAcaoInimigo(batalha);
    }

    private void executarAcaoInimigo(Batalha batalha) {
        Personagem heroi = batalha.getHeroi();
        Personagem inimigo = batalha.getInimigo();

        // IA simples: 30% de chance do inimigo usar ataque pesado
        if (random.nextInt(100) < 30) {
            inimigo.setEstrategiaAtaque(new AtaquePesadoStrategy());
        } else {
            inimigo.setEstrategiaAtaque(new AtaqueBasicoStrategy());
        }

        int danoInimigo = inimigo.executarAtaque(heroi);
        if (danoInimigo == 0) {
            batalha.notificarEvento(inimigo.getNome() + " tentou usar "
                    + inimigo.getEstrategiaAtaque().getNomeHabilidade() + ", mas ERROU!");
        } else {
            batalha.notificarEvento(inimigo.getNome() + " contra-atacou com "
                    + inimigo.getEstrategiaAtaque().getNomeHabilidade() + " e causou " + danoInimigo + " de dano!");
        }

        if (!heroi.estaVivo()) {
            finalizarComVencedor(batalha, inimigo, heroi);
            return;
        }

        batalha.incrementarTurno();
        batalha.notificarStatus();
    }

    private void finalizarComVencedor(Batalha batalha, Personagem vencedor, Personagem perdedor) {
        batalha.notificarStatus();
        batalha.setEstado(new EstadoFinalizado(vencedor, perdedor));
        batalha.notificarFimDeBatalha(vencedor, perdedor);
    }

    @Override
    public String getNomeEstado() {
        return "EM ANDAMENTO";
    }
}