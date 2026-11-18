package br.senac.rpg;

import br.senac.rpg.model.entity.Personagem;
import br.senac.rpg.model.factory.PersonagemFactory;
import br.senac.rpg.model.factory.TipoClasse;
import br.senac.rpg.model.strategy.AtaqueMagicoStrategy;
import br.senac.rpg.model.strategy.AtaquePesadoStrategy;

public class Main {
    public static void main(String[] args) {
        Personagem heroi = PersonagemFactory.criarHeroi(TipoClasse.GUERREIRO, "Arthur");
        Personagem inimigo = PersonagemFactory.criarInimigoPorNivel(1);

        int dano1 = heroi.executarAtaque(inimigo);
        System.out.println(heroi.getNome() + " usou " + heroi.getEstrategiaAtaque().getNomeHabilidade() + " -> Dano: " + dano1);

        heroi.setEstrategiaAtaque(new AtaquePesadoStrategy());
        int dano2 = heroi.executarAtaque(inimigo);
        System.out.println(heroi.getNome() + " usou " + heroi.getEstrategiaAtaque().getNomeHabilidade() + " -> Dano: " + dano2);

        heroi.setEstrategiaAtaque(new AtaqueMagicoStrategy());
        int dano3 = heroi.executarAtaque(inimigo);
        System.out.println(heroi.getNome() + " usou " + heroi.getEstrategiaAtaque().getNomeHabilidade() + " -> Dano: " + dano3);

        System.out.println("Status final do inimigo: " + inimigo);
    }
}