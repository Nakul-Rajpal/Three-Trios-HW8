package cs3500.threetrios.view.gui;

import cs3500.threetrios.controller.GameActions;
import cs3500.threetrios.view.ThreeTriosView;

/**
 * Behaviors for a ThreeTriosGUI that displays the game state and provides a visual interface
 * for users to interact with.
 */
public interface ThreeTriosGUI extends ThreeTriosView {

  /**
   * Adds listeners to this ThreeTriosGUI so that they can be alerted when certain events happen.
   *
   * @param actionListener the controller
   */
  public void addFeatureListener(GameActions actionListener);

  /**
   * Make the view visible (or invisible).
   *
   * @param show shows the GUI if true
   */
  public void makeVisible(boolean show);

}