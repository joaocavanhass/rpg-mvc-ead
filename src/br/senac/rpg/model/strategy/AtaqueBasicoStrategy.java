package br.senac.rpg.model.strategy;

import br.senac.rpg.model.entity.Personagem;

public class AtaqueBasicoStrategy implements EstrategiaAtaque {

    @Override
    public int calcularDano(Personagem atacante, Personagem alvo) {
        return atacante.getAtaqueBase();
    }

    @Override
    public String getNomeHabilidade() {
        return "Ataque Básico";
    }
}