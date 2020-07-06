package g49853.diamond.model;

import java.util.Arrays;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    /**
     * try to add explorers and verify the number of explorers in the list.
     */
    public void addAndGetExplorerGoodNumberOfExplorers() {
        System.out.println("addAndGetExplorerGoodNumberOfExplorers");
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        Explorer e2 = new Explorer("mcd");

        game.addExplorer(e1);
        game.addExplorer(e2);

        assertEquals(2, game.getExplorers().size());
    }

    /**
     * try to add explorers and verify if the list contains explorers.
     */
    @Test
    public void addAndGetExplorerGoodExplorers() {
        System.out.println("addAndGetExplorerGoodExplorers");
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        Explorer e2 = new Explorer("mcd");

        game.addExplorer(e1);
        game.addExplorer(e2);
        assertTrue(game.getExplorers().contains(e1)
                && game.getExplorers().contains(e2));
    }

    /**
     * try the state of an explorer who decided to leave the cave.
     */
    @Test
    public void treatChoiceToLeave() {
        System.out.println("treatChoiceToLeave");
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        game.addExplorer(e1);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        assertTrue(!game.exploringExplorers.contains(e1));
    }

    /**
     * try a runtime Exception with an explorer to remove from the game when he
     * is not in the current game.
     */
    @Test(expected = GameException.class)
    public void treatChoiceToLeaveException() {
        System.out.println("treatChoiceToLeaveException");
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        game.handleExplorerDecisionToLeave(e1);
    }

    /**
     * try the amount of rubies of a bag which is not in the cave.
     */
    @Test
    public void moveForwardLeavingExplorerDoNotGetRubies() {
        System.out.println("moveForwardLeavingExplorerDoNotGetRubies");
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        Explorer e2 = new Explorer("pbt");
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        game.moveForward();
        assertTrue(e1.getBag().getValue() == 0);
    }

    /**
     * try if the status of the game is over if there is no explorer.
     */
    @Test
    public void isOverNoExplorersTrue() {
        System.out.println("isOverNoExplorersTrue");
        Game game = new Game();
        assertTrue(game.isExplorationPhaseOver());
    }

    /**
     * try if the game is over or not while there is still an explorer who is
     * exploring.
     */
    @Test
    public void isOverExploringExplorer() {
        System.out.println("isOverExploringExplorerFalse");
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        game.addExplorer(e1);
        game.startNewExplorationPhase();
        assertFalse(game.isExplorationPhaseOver());
    }

    /**
     * try if the phase is over when there is no more explorer.
     */
    @Test
    public void isOverExplorerIsLeaving() {
        System.out.println("isOverExplorerIsLeaving");
        Game game = new Game();
        Explorer e1 = new Explorer("sdr");
        game.addExplorer(e1);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        assertTrue(game.isExplorationPhaseOver());
    }

    /**
     * try to add 2 players in a list of exploring explorers, then remove 1 and
     * get the correct size and the correct explorer in it.
     */
    @Test
    public void getExploringExplorers() {
        System.out.println("getExploringExplorers");
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        Explorer e2 = new Explorer("sdr");
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.startNewExplorationPhase();
        game.handleExplorerDecisionToLeave(e1);
        List<Explorer> exploringExplorers = game.getExploringExplorers();
        assertTrue(exploringExplorers.size() == 1
                && exploringExplorers.contains(e2));
    }

    /**
     * Test of start method, of class Game.
     */
    @Test(expected = GameException.class)
    public void testStart() {
        System.out.println("start");
        Game instance = new Game();
        instance.start();
    }

    /**
     * Test of isThereEnoughExplorer method, of class Game when there is no
     * explorer.
     */
    @Test
    public void testIsThereEnoughExplorerFalse() {
        System.out.println("isThereEnoughExplorerFalse");
        Game instance = new Game();
        boolean result = instance.isThereEnoughExplorer();
        assertFalse(result);
    }

    /**
     * Test of isThereEnoughExplorer method, of class Game when there are enough
     * explorers(3).
     */
    @Test
    public void testIsThereEnoughExplorerTrue() {
        System.out.println("isThereEnoughExplorerTrue");
        Game instance = new Game();
        Explorer expl = new Explorer("Selim");
        for (int x = 0; x < 3; x++) {
            instance.addExplorer(expl);
        }
        boolean result = instance.isThereEnoughExplorer();
        assertTrue(result);
    }

    /**
     * Test of isItPossibleToAddExplorer method, of class Game when there is no
     * explorer.
     */
    @Test
    public void testIsItPossibleToAddExplorerTrue() {
        System.out.println("isItPossibleToAddExplorer");
        Game instance = new Game();
        boolean result = instance.isItPossibleToAddExplorer();
        assertTrue(result);
    }

    /**
     * Test of isItPossibleToAddExplorer method, of class Game when there is too
     * many explorers(9).
     */
    @Test
    public void testIsItPossibleToAddExplorerFalse() {
        System.out.println("isItPossibleToAddExplorerFalse");
        Game instance = new Game();
        Explorer expl = new Explorer("Selim");
        for (int x = 0; x < 9; x++) {
            instance.addExplorer(expl);
        }
        boolean result = instance.isItPossibleToAddExplorer();
        assertFalse(result);
    }

    /**
     * Test of getWinner method, of class Game.
     *
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Game game = new Game();

        for (int x = 0; x < 5; x++) {
            game.startNewExplorationPhase();
            game.endExplorationPhase();
        }
        int exp = 0;
        int res = game.getWinner().getFortune();
        assertEquals(exp, res);
    }

    /**
     * Test of makeExplorersLeave method, of class Game.
     */
    @Test
    public void testMakeExplorersLeave() {
        System.out.println("makeExplorersLeave");
        Game game = new Game();
        Explorer e1 = new Explorer("pbt");
        Explorer e2 = new Explorer("sdr");
        Treasure treasure = new Treasure(6);
        game.addExplorer(e1);
        game.addExplorer(e2);
        game.startNewExplorationPhase();
        game.cave.getCurrentEntrance().setLastDiscoveredTile(treasure);
        e2.takeDecisionToLeave();
        game.makeExplorersLeave();
        assertTrue(game.getExploringExplorers().size() == 1 && game.exploringExplorers.contains(e1));
    }

    /**
     * Test of isExplorationPhaseAborted method, of class Game.
     */
    @Test
    public void testIsExplorationPhaseAbortedFalse() {
        System.out.println("isExplorationPhaseAbortedFalse");
        Game instance = new Game();
        assertFalse(instance.isExplorationPhaseAborted());
    }

    /**
     * Test of isExplorationPhaseAborted method if it is unsafe, of class Game.
     */
    @Test
    public void testIsExplorationPhaseAbortedTrue() {
        System.out.println("isExplorationPhaseAbortedTrue");
        Game instance = new Game();
        instance.cave.openNewEntrance();
        instance.cave.getCurrentEntrance().unSafe = true;
        assertTrue(instance.isExplorationPhaseAborted());
    }

    /**
     * Test of isExplorationPhaseOver method, of class Game.
     */
    @Test
    public void testIsExplorationPhaseOver() {
        System.out.println("isExplorationPhaseOver");
        Game instance = new Game();
        instance.cave.openNewEntrance();
        instance.cave.lockOutCurrentEntrance();
        assertTrue(instance.isExplorationPhaseOver());
    }

    /**
     * Test of getCave method, of class Game.
     */
    @Test
    public void testGetCave() {
        System.out.println("getCave");
        Game instance = new Game();
        boolean res = instance.cave.getDeck().tiles.size() == 38;
        assertTrue(res);
    }

    /**
     * Test of getExplorers method with one explorer, of class Game.
     */
    @Test
    public void testGetExplorers() {
        System.out.println("getExplorers");
        Game instance = new Game();
        Explorer e1 = new Explorer("Selim");
        instance.addExplorer(e1);
        assertEquals(1, instance.getExplorers().size());

    }

    /**
     * Test of getExploringExplorers method, of class Game.
     */
    @Test
    public void testGetExploringExplorers() {
        System.out.println("getExploringExplorers");
        Game instance = new Game();
        Explorer e1 = new Explorer("Selim");
        instance.addExplorer(e1);
        instance.startNewExplorationPhase();
        assertEquals(1, instance.getExploringExplorers().size());
    }

    /**
     * Test of startNewExplorationPhase method, of class Game.
     */
    @Test
    public void testStartNewExplorationPhase() {
        System.out.println("startNewExplorationPhase");
        Game instance = new Game();
        Explorer e1 = new Explorer("Selim");
        instance.addExplorer(e1);
        instance.startNewExplorationPhase();
        assertEquals(State.EXPLORING, instance.getExploringExplorers().get(0).getState());

    }

    /**
     * Test of endExplorationPhase method, of class Game.
     */
    @Test
    public void testEndExplorationPhase() {
        System.out.println("endExplorationPhase");
        Game instance = new Game();
        instance.cave.openNewEntrance();
        instance.endExplorationPhase();
        assertTrue(instance.cave.getCurrentEntrance().isLockedOut());
    }

    /**
     * Test of isOver method, of class Game.
     */
    @Test
    public void testIsOverFalse() {
        System.out.println("isOverFalse");
        Game instance = new Game();
        Explorer e1 = new Explorer("selim");
        instance.addExplorer(e1);
        instance.startNewExplorationPhase();
        assertFalse(instance.isOver());

    }

    /**
     * Test of isOver method if it is really over, of class Game.
     */
    @Test
    public void testIsOverTrue() {
        System.out.println("isOverTrue");
        Game instance = new Game();
        Explorer e1 = new Explorer("selim");
        instance.addExplorer(e1);
        for (int x = 0; x < 5; x++) {
            instance.startNewExplorationPhase();
            instance.endExplorationPhase();
        }
        assertTrue(instance.isOver());

    }
}
