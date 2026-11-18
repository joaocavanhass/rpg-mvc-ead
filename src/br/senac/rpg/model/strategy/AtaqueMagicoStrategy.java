package br.senac.rpg.model.strategy;

import br.senac.rpg.model.entity.Personagem;

public class AtaqueMagicoStrategy implements EstrategiaAtaque {

    @Override
    public int calcularDano(Personagem atacante, Personagem alvo) {
        // Adiciona bônus fixo que compensa a defesa do oponente
        return atacante.getAtaqueBase() + (alvo.getDefesa() / 2) + 4;
    }

    @Override
    public String getNomeHabilidade() {
        return "Disparo Arcano (Perfura Defesa)";
    }
}