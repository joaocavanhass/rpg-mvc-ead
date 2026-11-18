package br.senac.rpg.model.strategy;

import br.senac.rpg.model.entity.Personagem;

public interface EstrategiaAtaque {
    int calcularDano(Personagem atacante, Personagem alvo);
    String getNomeHabilidade();
}