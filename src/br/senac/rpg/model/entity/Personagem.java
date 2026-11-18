package br.senac.rpg.model.entity;

public class Personagem {
    private final String nome;
    private final String classe;
    private final int vidaMaxima;
    private int vidaAtual;
    private int ataqueBase;
    private int defesa;

    public Personagem(String nome, String classe, int vidaMaxima, int ataqueBase, int defesa) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do personagem não pode ser vazio.");
        }
        this.nome = nome.trim();
        this.classe = classe;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataqueBase = ataqueBase;
        this.defesa = defesa;
    }

    public int receberDano(int danoBruto) {
        int danoReal = Math.max(1, danoBruto - (defesa / 2));
        this.vidaAtual = Math.max(0, this.vidaAtual - danoReal);
        return danoReal;
    }

    public int curar(int quantidade) {
        if (!estaVivo()) {
            throw new IllegalStateException("Não é possível curar um personagem derrotado.");
        }
        int vidaAntes = this.vidaAtual;
        this.vidaAtual = Math.min(this.vidaMaxima, this.vidaAtual + quantidade);
        return this.vidaAtual - vidaAntes;
    }

    public boolean estaVivo() {
        return this.vidaAtual > 0;
    }

    public String getNome() { return nome; }
    public String getClasse() { return classe; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getVidaAtual() { return vidaAtual; }
    public int getAtaqueBase() { return ataqueBase; }
    public int getDefesa() { return defesa; }

    @Override
    public String toString() {
        return String.format("%s [%s] | HP: %d/%d | ATQ: %d | DEF: %d",
                nome, classe, vidaAtual, vidaMaxima, ataqueBase, defesa);
    }
}
