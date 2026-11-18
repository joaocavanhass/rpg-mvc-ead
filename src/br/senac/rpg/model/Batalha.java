package br.senac.rpg.model;

import br.senac.rpg.model.entity.Personagem;
import br.senac.rpg.model.observer.BatalhaObserver;
import br.senac.rpg.model.state.EstadoBatalha;
import br.senac.rpg.model.state.EstadoPreparacao;
import br.senac.rpg.model.strategy.EstrategiaAtaque;
import java.util.ArrayList;
import java.util.List;

public class Batalha {
    private final Personagem heroi;
    private final Personagem inimigo;
    private EstadoBatalha estadoAtual;
    private int turno;
    private int pocoesRestantes;
    private final List<BatalhaObserver> observadores;

    public Batalha(Personagem heroi, Personagem inimigo) {
        if (heroi == null || inimigo == null) {
            throw new IllegalArgumentException("Herói e inimigo são obrigatórios para iniciar uma batalha.");
        }
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.estadoAtual = new EstadoPreparacao();
        this.turno = 1;
        this.pocoesRestantes = 2; // Cada herói começa com 2 poções
        this.observadores = new ArrayList<>();
    }

    // Métodos do Observer (Subject)
    public void adicionarObserver(BatalhaObserver observer) {
        if (observer != null && !observadores.contains(observer)) {
            observadores.add(observer);
        }
    }

    public void notificarEvento(String mensagem) {
        for (BatalhaObserver obs : observadores) {
            obs.onEventoBatalha(mensagem);
        }
    }

    public void notificarStatus() {
        for (BatalhaObserver obs : observadores) {
            obs.onAtualizacaoStatus(heroi, inimigo, turno);
        }
    }

    public void notificarFimDeBatalha(Personagem vencedor, Personagem perdedor) {
        for (BatalhaObserver obs : observadores) {
            obs.onBatalhaFinalizada(vencedor, perdedor);
        }
    }

    // Métodos delegados ao State
    public void iniciarBatalha() {
        estadoAtual.iniciar(this);
    }

    public void realizarAtaque(EstrategiaAtaque estrategia) {
        estadoAtual.executarTurnoAtaque(this, estrategia);
    }

    public void usarPocaoCura() {
        estadoAtual.executarTurnoCura(this);
    }

    // Controle interno de estado e turnos
    public void setEstado(EstadoBatalha novoEstado) {
        this.estadoAtual = novoEstado;
    }

    public void incrementarTurno() {
        this.turno++;
    }

    public void consumirPocao() {
        if (this.pocoesRestantes > 0) {
            this.pocoesRestantes--;
        }
    }

    public Personagem getHeroi() { return heroi; }
    public Personagem getInimigo() { return inimigo; }
    public EstadoBatalha getEstadoAtual() { return estadoAtual; }
    public int getTurno() { return turno; }
    public int getPocoesRestantes() { return pocoesRestantes; }
}