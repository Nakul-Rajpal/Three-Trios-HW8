package cs3500.threetrios.view.gui;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import cs3500.threetrios.controller.GameActions;
import cs3500.threetrios.model.ThreeTriosReadOnlyModel;

/**
 * Implementation for a ThreeTriosGUI that uses Java Swing.
 * Has the ability to add components as listeners to this view, and alert them when
 * certain events take place.
 */
public class ThreeTriosGraphicsView extends JFrame implements ThreeTriosGUI {

  private final TriosPanel triosPanel;

  private final ThreeTriosReadOnlyModel model;

  /**
   * Constructs a ThreeTriosGraphicsView.
   *
   * @param model the read only model the view renders the game state based on
   */
  public ThreeTriosGraphicsView(ThreeTriosReadOnlyModel model) throws IOException {
    super();
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.setSize(1400, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.model = model;
    this.triosPanel = new TriosPanel(this.model);
    this.add(this.triosPanel);

  }

  @Override
  public void addFeatureListener(GameActions actionListener) {
    this.triosPanel.addFeaturesListener(actionListener);
  }

  @Override
  public void makeVisible(boolean show) {
    this.pack();
    this.setVisible(show);
  }

  @Override
  public void render() throws IOException {
    this.setTitle("Current Player: " + model.getCurrentPlayer().toString());
    this.repaint();
  }

  @Override
  public void showPopup(String message) {
    JOptionPane.showMessageDialog(new JFrame(), message, "MESSAGE", JOptionPane.PLAIN_MESSAGE);
  }
}
