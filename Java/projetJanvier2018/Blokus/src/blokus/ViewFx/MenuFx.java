package blokus.ViewFx;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author selim
 */
public class MenuFx {

    private final Menu[] names;
    private final MenuItem[] fileItems;
    private final MenuBar menu;

    public MenuFx() {

        names = new Menu[]{new Menu("File"), new Menu("Help")};
        fileItems = new MenuItem[]{new MenuItem("New Game"), new MenuItem("History"), new MenuItem("Exit")};
        menu = new MenuBar(names);

        names[0].getItems().addAll(fileItems);

    }

    public MenuBar getMenuBar() {
        return menu;
    }

    public MenuItem getNewGame() {
        
        return fileItems[0];
    }

    public MenuItem getHistory() {
        
        return fileItems[1];
    }

    public MenuItem getExit() {
        
        return fileItems[2];
    }

}
