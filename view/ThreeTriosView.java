package cs3500.threetrios.view;

import java.io.IOException;

/**
 * Behaviors for a ThreeTriosView.
 */
public interface ThreeTriosView {

  /**
   * Renders a model in some manner (e.g. as text, or as graphics, etc.).
   *
   * @throws IOException if the rendering fails
   */
  void render() throws IOException;

  /**
   * Displays the given message as a popup in the view.
   *
   * @param message the message
   */
  void showPopup(String message);
}
