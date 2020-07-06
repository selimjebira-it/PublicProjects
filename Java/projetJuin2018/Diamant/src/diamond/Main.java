package g49853.diamond;

import g49853.diamond.controller.Controller;
import g49853.diamond.model.Game;
import g49853.diamond.model.Model;
import g49853.diamond.view.View;

public class Main {

    public static void main(String[] args) {
        Model game = new Game();
        View view = new View(game);
        Controller controller = new Controller(game, view);
        controller.startGame();
    }
}
