package br.senac.rpg.model.observer;

import br.senac.rpg.model.entity.Personagem;

public interface BatalhaObserver {
    void onEventoBatalha(String mensagem);
    void onAtualizacaoStatus(Personagem heroi, Personagem inimigo, int turno);
    void onBatalhaFinalizada(Personagem vencedor, Personagem perdedor);
}