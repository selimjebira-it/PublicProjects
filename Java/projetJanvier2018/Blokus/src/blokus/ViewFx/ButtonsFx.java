package blokus.ViewFx;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author selim
 */
public class ButtonsFx extends HBox {

    private final ArrayList<Button> buttons;
    private static final String[] BUTTONS_NAMES = {"new Game", "Skip", "leave"};

    public ButtonsFx() {

        buttons = new ArrayList<>();
        for (String str : BUTTONS_NAMES) {
            Button button = new Button(str);
            buttons.add(button);
            this.getChildren().add(button);
        }
    }

    public Button getRestart() {
        return buttons.get(0);
    }

    public Button getSkip() {

        return buttons.get(1);
    }

    public Button getLeave() {
        return buttons.get(2);
    }

}
