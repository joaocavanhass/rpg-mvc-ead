package br.senac.rpg.model.entity;

public class Inimigo extends Personagem {
    public Inimigo(String nome, int vidaMaxima, int ataqueBase, int defesa) {
        super(nome, "Monstro", vidaMaxima, ataqueBase, defesa);
    }
}