package br.senac.rpg.model.strategy;

import br.senac.rpg.model.entity.Personagem;
import java.util.Random;

public class AtaquePesadoStrategy implements EstrategiaAtaque {
    private final Random random = new Random();

    @Override
    public int calcularDano(Personagem atacante, Personagem alvo) {
        // 70% de chance de acertar um golpe devastador
        if (random.nextInt(100) < 70) {
            return (int) Math.round(atacante.getAtaqueBase() * 1.5);
        }
        return 0; // Errou o golpe
    }

    @Override
    public String getNomeHabilidade() {
        return "Golpe Pesado (Alto Risco)";
    }
}