package br.senac.rpg;

import br.senac.rpg.model.entity.Personagem;
import br.senac.rpg.model.factory.PersonagemFactory;
import br.senac.rpg.model.factory.TipoClasse;

public class Main {
    public static void main(String[] args) {
        Personagem heroi = PersonagemFactory.criarHeroi(TipoClasse.GUERREIRO, "Arthur");
        Personagem inimigo = PersonagemFactory.criarInimigoPorNivel(1);

        System.out.println("Herói criado: " + heroi);
        System.out.println("Inimigo criado: " + inimigo);
    }
}