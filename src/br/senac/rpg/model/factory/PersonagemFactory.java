package br.senac.rpg.model.factory;

import br.senac.rpg.model.entity.*;

public class PersonagemFactory {

    public static Personagem criarHeroi(TipoClasse tipo, String nome) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de classe inválido.");
        }
        return switch (tipo) {
            case GUERREIRO -> new Guerreiro(nome);
            case MAGO -> new Mago(nome);
            case ARQUEIRO -> new Arqueiro(nome);
        };
    }

    public static Personagem criarInimigoPorNivel(int nivel) {
        return switch (nivel) {
            case 1 -> new Inimigo("Goblin Saqueador", 90, 18, 6);
            case 2 -> new Inimigo("Cavaleiro Sombrio", 130, 24, 12);
            case 3 -> new Inimigo("Dragão Ancião", 180, 30, 15);
            default -> throw new IllegalArgumentException("Nível de inimigo inválido: " + nivel);
        };
    }
}