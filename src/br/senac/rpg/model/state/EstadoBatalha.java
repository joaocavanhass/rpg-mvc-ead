package br.senac.rpg.model.state;

import br.senac.rpg.model.Batalha;
import br.senac.rpg.model.strategy.EstrategiaAtaque;

public interface EstadoBatalha {
    void iniciar(Batalha batalha);
    void executarTurnoAtaque(Batalha batalha, EstrategiaAtaque estrategia);
    void executarTurnoCura(Batalha batalha);
    String getNomeEstado();
}