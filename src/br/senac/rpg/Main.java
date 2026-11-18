package br.senac.rpg;

import br.senac.rpg.controller.JogoController;
import br.senac.rpg.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        JogoController controller = new JogoController(view);
        controller.iniciarAplicacao();
    }
}