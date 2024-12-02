package cs3500.threetrios.controller.player;

import cs3500.threetrios.controller.GameActions;
import cs3500.threetrios.model.components.Player;

/**
 * Represents a Player playing a game of ThreeTrios (Different from Player(RED or BLUE).
 */
public interface ThreeTriosPlayer {

  /**
   * Alerts the ThreeTriosPlayer that it is their turn to play.
   */
  public void yourTurn();

  /**
   * Adds the given component as a listener to the ThreeTriosPlayer,
   * so that the ThreeTriosPlayer can alert them when they've made a move.
   * @param features the component that will now listen to the Player
   */
  public void addFeatureListener(GameActions features);

  /**
   * Returns the player(RED or BLUE) associated with this ThreeTriosPlayer.
   * @return the player this ThreeTriosPlayer is playing for in the game
   */
  public Player getPlayer();

}
