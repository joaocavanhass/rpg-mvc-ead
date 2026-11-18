package br.senac.rpg.model.state;

import br.senac.rpg.model.Batalha;
import br.senac.rpg.model.strategy.EstrategiaAtaque;

public class EstadoPreparacao implements EstadoBatalha {

    @Override
    public void iniciar(Batalha batalha) {
        batalha.notificarEvento("⚔️ A batalha começou entre " + batalha.getHeroi().getNome()
                + " e " + batalha.getInimigo().getNome() + "!");
        batalha.setEstado(new EstadoEmAndamento());
        batalha.notificarStatus();
    }

    @Override
    public void executarTurnoAtaque(Batalha batalha, EstrategiaAtaque estrategia) {
        throw new IllegalStateException("A batalha ainda está em preparação. Inicie a partida primeiro.");
    }

    @Override
    public void executarTurnoCura(Batalha batalha) {
        throw new IllegalStateException("A batalha ainda está em preparação. Inicie a partida primeiro.");
    }

    @Override
    public String getNomeEstado() {
        return "PREPARAÇÃO";
    }
}