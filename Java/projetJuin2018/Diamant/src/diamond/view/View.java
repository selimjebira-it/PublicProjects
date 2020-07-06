package g49853.diamond.view;

import g49853.diamond.model.Explorer;
import g49853.diamond.model.Model;
import g49853.diamond.model.Tile;
import g49853.diamond.model.Treasure;
import java.util.Scanner;

public class View {

    Scanner scan;
    Model game;

    /**
     * Construct the view, create an object scan and a game.
     *
     * @param game
     */
    public View(Model game) {
        this.scan = new Scanner(System.in);
        this.game = game;
    }

    /**
     * method that creates an explorer and ask the name of this Explorer.
     *
     * @return the explorer
     */
    public Explorer askExplorer() {
        int MaxChar = 8;
        String MSG = "please enter the name of the explorer(max " + MaxChar + " characters)";
        String nom;
        do {
            System.out.println(MSG);
            nom = scan.next();
        } while (nom.length() > MaxChar);
        Explorer joueur = new Explorer(nom);
        return joueur;
    }

    /**
     * method that ask if there is a new explorer to add to the game.
     *
     * @return the answer
     */
    public boolean isThereANewExplorerToAdd() {
        String yes = "yes";
        String no = "no";
        String MSG = "is there an other explorer?(" + yes + "/" + no + ")";
        String entry;
        do {
            System.out.println(MSG);
            entry = scan.next();
        } while (!entry.equals(yes) && !entry.equals(no));
        return entry.equals(yes);
    }

    /**
     * method that ask if an explorer want to leave the game.
     *
     * @param explorer the explorer
     * @return return the answer
     */
    public boolean askExplorerChoiceToContinue(Explorer explorer) {
        String yes = "yes";
        String no = "no";
        String nomJ = explorer.getPseudonyme();
        String MSG = nomJ + " ,would you like to continue ?(" + yes + "/" + no + ")";
        String entry;
        do {
            System.out.println(MSG);
            entry = scan.next();
        } while (!entry.equals(yes) && !entry.equals(no));

        return entry.equals(yes);
    }

    public void displayIntro() {
        System.out.println("BIENVENUE sur le Jeu de DIAMANT");
        espace();
    }

    public void displayPhase() {
        espace();
        explorersChest();
        espace();
        manche();
        espace();
    }

    public void manche() {
        int nbExploredEntrance = this.game.getCave().getNbExploredEntrance() + 1;
        System.out.println("Manche:" + nbExploredEntrance);
    }

    /**
     * method that shows the last Tile found.
     */
    public void explorersChest() {

        this.game.getExplorers().forEach((expl) -> {
            System.out.println("[" + expl.getPseudonyme() + ", Chest :" + expl.getFortune() + "]");
        });
    }

    /**
     * informations needed for a turn.
     */
    public void displayTurn() {
        path();
        lastTile();
        explorersBag();
    }

    /**
     * display last tile found.
     */
    public void lastTile() {
        Treasure treasure;
        Tile lastTreasure = this.game.getCave().getCurrentEntrance().getLastDiscoveredTile();
        String value = lastTreasure.getValue();
        if (lastTreasure.getClass() == Treasure.class) {
            treasure = (Treasure) lastTreasure;
            value = "Treasure : " + treasure.getInitNbGems();
        }
        String MSGT = "Last tile found : " + value;
        System.out.println(MSGT);
        System.out.println("");
    }

    /**
     * display the path
     */
    public void path() {

        System.out.print("Path :");
        for (int x = 0; x < this.game.getCave().getCurrentEntrance().getPath().size(); x++) {
            Tile tile = this.game.getCave().getCurrentEntrance().getPath().get(x);
            System.out.print("[" + tile.getValue() + "]");

        }
        System.out.println("");
    }

    /**
     * display explorers bag and the name of the explorers.
     */
    public void explorersBag() {
        this.game.getExplorers().forEach((expl) -> {
            System.out.println("[" + expl.getPseudonyme() + ", Bag :" + expl.getBag().getValue() + "]");
        });
    }

    /**
     * method that display the final message and the status of players name and
     * rubies.
     */
    public void displayWinner() {
        espace();
        String MSGFIN = "The game is over.";
        System.out.println(MSGFIN);
        Explorer expl = this.game.getWinner();
        int rubies = expl.getFortune();
        System.out.println("WINNER :" + expl.getPseudonyme() + " ,Rubies :" + rubies);
    }

    /**
     * display the name of players who have been trapped in the cave entrance.
     */
    public void displayRunAway() {

        this.game.getExploringExplorers().forEach((expl) -> {
            System.out.println(expl.getPseudonyme() + " a été piégé!");
        });
        System.out.println("Fuyez pauvres fous ! ");
    }

    /**
     * put some lines to separate.
     */
    public void espace() {
        for (int x = 0; x < 5; x++) {
            System.out.println("");
        }
    }
}
