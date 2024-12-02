package cs3500.threetrios.model;

import cs3500.threetrios.model.components.Player;

/**
 * Information that listeners of a ModelBroadcaster can be alerted to.
 */
public interface ModelFeatures {

  /**
   * Alerts listeners that it is now the given players turn.
   */
  public void alertTurn(Player player);

  /**
   * Alerts listeners that the game is over.
   */
  public void alertGameOver();

  /**
   * Alerts listeners that the game has started.
   */
  public void alertGameStart();

}
