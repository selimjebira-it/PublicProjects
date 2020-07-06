package g49853.diamond.controller;

import g49853.diamond.model.Explorer;
import g49853.diamond.model.Model;
import g49853.diamond.model.State;
import g49853.diamond.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    private Model game;
    private View view;

    public Controller(Model game, View view) {
        this.game = game;
        this.view = view;
    }

    /**
     * method that launch the game
     */
    public void startGame() {
        this.view.displayIntro();
        initJoueurs();
        while (!game.isOver()) {
            while (game.getCave().hasNewEntranceToExplore()) {
                phase();
            }

        }
        view.displayWinner();

    }

    /**
     * add players to the game
     */
    public void initJoueurs() {
        Explorer expl;
        boolean addJ = true;
        do {
            if (this.game.isThereEnoughExplorer()) {
                addJ = this.view.isThereANewExplorerToAdd();
            }
            if (addJ) {
                expl = view.askExplorer();
                this.game.addExplorer(expl);
            }
        } while (this.game.isItPossibleToAddExplorer() && addJ);
    }

    public void phase() {
        this.game.startNewExplorationPhase();
        while (!this.game.getCave().getCurrentEntrance().isLockedOut()) {
            this.view.displayPhase();
            round();
        }

    }

    /**
     * one round of the game
     */
    public void round() {

        this.game.moveForward();
        if (!this.game.isExplorationPhaseAborted()) {
            view.displayTurn();
            askExplorers();
        } else {
            view.displayRunAway();
            this.game.endExplorationPhase();
        }
        closeIfneeded();

    }

    /**
     * take out explorers and close the entrance if it is over
     */
    private void closeIfneeded() {
        if (this.game.isExplorationPhaseOver() && !this.game.isExplorationPhaseAborted()) {
            this.game.endExplorationPhase();
        }
    }

    /**
     * ask to explorers if they want to leave the entrance
     */
    private void askExplorers() {
        List<Explorer> listJExploring;
        listJExploring = this.game.getExploringExplorers().stream()
                .filter(x -> x.getState() == State.EXPLORING).collect(Collectors.toList());
        List<Explorer> listJ = copyExploringExplorerList(listJExploring);
        Explorer expl;
        for (int x = 0; x < listJ.size(); x++) {
            expl = listJ.get(x);
            if (!this.view.askExplorerChoiceToContinue(expl)) {
                expl.takeDecisionToLeave();
            }

        }
        this.game.makeExplorersLeave();
    }

    /**
     *
     * method that copy the exploringList
     *
     * @param explorerL list of explorer we want to copy
     * @return list of explorers
     */
    public List<Explorer> copyExploringExplorerList(List<Explorer> explorerL) {
        List<Explorer> resList = new ArrayList<>();
        for (int j = 0; j < explorerL.size(); j++) {
            resList.add((Explorer) explorerL.get(j));
        }
        return resList;
    }
}
