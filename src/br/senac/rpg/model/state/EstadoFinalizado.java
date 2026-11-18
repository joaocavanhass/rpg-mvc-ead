package br.senac.rpg.model.state;

import br.senac.rpg.model.Batalha;
import br.senac.rpg.model.entity.Personagem;
import br.senac.rpg.model.strategy.EstrategiaAtaque;

public class EstadoFinalizado implements EstadoBatalha {
    private final Personagem vencedor;
    private final Personagem perdedor;

    public EstadoFinalizado(Personagem vencedor, Personagem perdedor) {
        this.vencedor = vencedor;
        this.perdedor = perdedor;
    }

    @Override
    public void iniciar(Batalha batalha) {
        throw new IllegalStateException("Esta batalha já foi encerrada. Crie uma nova batalha.");
    }

    @Override
    public void executarTurnoAtaque(Batalha batalha, EstrategiaAtaque estrategia) {
        throw new IllegalStateException("Ação inválida: a batalha já terminou! Vencedor: " + vencedor.getNome());
    }

    @Override
    public void executarTurnoCura(Batalha batalha) {
        throw new IllegalStateException("Ação inválida: a batalha já terminou! Vencedor: " + vencedor.getNome());
    }

    public Personagem getVencedor() { return vencedor; }
    public Personagem getPerdedor() { return perdedor; }

    @Override
    public String getNomeEstado() {
        return "FINALIZADA";
    }
}