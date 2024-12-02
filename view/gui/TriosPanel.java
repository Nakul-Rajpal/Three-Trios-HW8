package cs3500.threetrios.view.gui;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import cs3500.threetrios.controller.GameActions;
import cs3500.threetrios.model.ThreeTriosReadOnlyModel;
import cs3500.threetrios.model.components.Card;
import cs3500.threetrios.model.components.Direction;
import cs3500.threetrios.model.components.Player;

/**
 * Represents a TriosPanel for rendering the game state of a ThreeTriosGUI.
 * The basic layout is a vertical hand on each side, with a grid in the middle. The
 * hands are clickable to select cards, and the board is clickable to select board slots.
 */
public class TriosPanel extends JPanel {

  private final ThreeTriosReadOnlyModel model;

  private int handWidth;

  private final List<GameActions> featuresListeners;

  private int selectedHandIndex;

  /**
   * Constructor for a TriosPanel.
   *
   * @param model the read only model used to render the game state on the panel
   * @throws IllegalArgumentException if the model is null
   */
  public TriosPanel(ThreeTriosReadOnlyModel model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }

    this.model = model;
    this.selectedHandIndex = -2;
    this.featuresListeners = new ArrayList<>();
    MouseListener mouseListener = new ThreeTriosClickListener();
    this.addMouseListener(mouseListener);
  }

  /**
   * Adds the given components as listeners to this panel.
   *
   * @param actionListener the view listeners
   * @throws IllegalArgumentException if the actionListener is null
   */
  public void addFeaturesListener(GameActions actionListener) {
    if (actionListener == null) {
      throw new IllegalArgumentException("features cannot be null");
    }
    this.featuresListeners.add(actionListener);
  }

  // set the currently selected hand index and alert listeners
  private void setHandIndex(MouseEvent e) throws NoninvertibleTransformException {
    if (this.model.isGameOver()) {
      return;
    }
    AffineTransform physicalToLogical = this.transformLogicalToPhysical().createInverse();

    int minX = 0;
    int maxX = this.getPreferredSize().width;
    int x = (int) (e.getX() * physicalToLogical.getScaleX());
    int y = (int) (e.getY() * physicalToLogical.getScaleY());

    Player current = this.model.getCurrentPlayer();
    if (current.equals(Player.Red)) {
      maxX = this.handWidth;
    } else {
      minX = maxX - this.handWidth;
    }


    if (x >= minX && x <= maxX) {
      int heightPerCard = (this.getPreferredSize().height / this.model.getHand(current).size());
      int newIndex = y / heightPerCard;
      if (newIndex != this.selectedHandIndex) {
        this.selectedHandIndex = y / heightPerCard;
      } else {
        this.selectedHandIndex = -2;
      }
    } else {
      //-2 so it doesn't clip into the screen
      this.selectedHandIndex = -2;
    }
    for (GameActions feature : this.featuresListeners) {
      feature.selectedCardInHand(this.selectedHandIndex);
    }
    this.repaint();
  }

  // set the currently selected hand index and alert listeners
  private void setBoardIndex(MouseEvent e) throws NoninvertibleTransformException {
    if (this.model.isGameOver()) {
      return;
    }
    AffineTransform physicalToLogical = this.transformLogicalToPhysical().createInverse();
    int col;
    int row;

    int minX = this.handWidth;
    int maxX = this.getPreferredSize().width - this.handWidth;
    int x = (int) (e.getX() * physicalToLogical.getScaleX());
    int y = (int) (e.getY() * physicalToLogical.getScaleY());

    if (x > maxX || x < minX) {
      col = -1;
      row = -1;
    } else {
      int heightPerCard = (this.getPreferredSize().height / this.model.getBoardHeight());
      int widthPerCard = (this.getPreferredSize().width - 2 * this.handWidth)
              / this.model.getBoardWidth();
      col = (x - minX) / widthPerCard;
      row = y / heightPerCard;
    }

    for (GameActions feature : this.featuresListeners) {
      feature.selectedBoardSlot(row, col);
    }

  }

  // private class for a ClickListener
  private class ThreeTriosClickListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      try {
        setBoardIndex(e);
        setHandIndex(e);
      } catch (NoninvertibleTransformException ex) {
        //It's always invertible, so this should never happen
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      //TODO:
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      //TODO:
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //TODO:
    }

    @Override
    public void mouseExited(MouseEvent e) {
      //TODO:
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.transform(transformLogicalToPhysical());

    this.drawHand(g2d, Player.Red);
    this.drawHand(g2d, Player.Blue);
    this.drawBoard(g2d);

    this.drawBoardBorder(g2d);
    this.drawSelected(g2d);
  }

  // draws the border around the last clicked card
  private void drawSelected(Graphics2D g2d) {
    Color brush = g2d.getColor();
    g2d.setColor(Color.BLACK);
    Stroke stroke = g2d.getStroke();
    g2d.setStroke(new BasicStroke((float) this.getPreferredSize().width / 200));
    Player current = this.model.getCurrentPlayer();
    int offset = 0;
    if (current == Player.Blue) {
      offset = this.getPreferredSize().width - this.handWidth;
    }


    int slotWidth = this.handWidth;
    int slotHeight = this.getPreferredSize().height /
            this.model.getHand(current).size();

    g2d.drawRect(offset, slotHeight * this.selectedHandIndex, slotWidth, slotHeight);

    g2d.setStroke(stroke);
    g2d.setColor(brush);
  }

  // draws the border around the board grid
  private void drawBoardBorder(Graphics2D g2d) {
    Color brush = g2d.getColor();
    g2d.setColor(Color.BLACK);
    Stroke stroke = g2d.getStroke();
    g2d.setStroke(new BasicStroke((float) this.getPreferredSize().width / 200));
    int startY = 0;
    int startX = this.handWidth;
    g2d.drawRect(startX, startY,
            this.getPreferredSize().width - 2 * this.handWidth,
            this.getPreferredSize().height);
    g2d.setStroke(stroke);
    g2d.setColor(brush);
  }

  // draws a hand based on the given player (in the range 0-200 x and 1200-1400 x and the entire y)
  private void drawHand(Graphics2D g2d, Player player) {
    ArrayList<Card> cards = new ArrayList<>(this.model.getHand(player));
    if (cards.isEmpty()) {
      return;
    }
    int offset = 0;
    if (player == Player.Blue) {
      offset = this.getPreferredSize().width - this.handWidth;
    }

    int slotWidth = this.handWidth;
    int slotHeight = this.getPreferredSize().height / cards.size();

    Color brush = g2d.getColor();
    this.setBrushPlayer(g2d, player);

    for (int i = 0; i < cards.size(); i++) {
      Card card = cards.get(i);
      int y = (i * slotHeight);
      this.drawCard(g2d, card, offset, y, slotWidth, slotHeight);
    }

    g2d.setColor(brush);
  }

  // draws a Card based on the given card
  private void drawCard(Graphics2D g2d, Card card, int x, int y, int width, int height) {
    g2d.fillRect(x, y, width, height);
    Color brush = g2d.getColor();

    int fontSize = Math.min(height / 4, width / 4);
    g2d.setFont(new Font("Serif", Font.PLAIN, fontSize));
    FontMetrics metrics = g2d.getFontMetrics();

    String north = card.getAttackValue(Direction.North).toString();
    String south = card.getAttackValue(Direction.South).toString();
    String east = card.getAttackValue(Direction.East).toString();
    String west = card.getAttackValue(Direction.West).toString();

    int northOffset = metrics.stringWidth(north) / 2;
    int southOffset = metrics.stringWidth(south) / 2;
    int eastOffset = metrics.stringWidth(east) / 2;
    int westOffset = metrics.stringWidth(west) / 2;
    int yOffset = metrics.getHeight() / 2;

    g2d.setColor(Color.BLACK);
    g2d.drawRect(x, y, width, height);
    g2d.drawString(north,
            x + width / 2 - northOffset, y + (height) / 4 + yOffset);
    g2d.drawString(south,
            x + width / 2 - southOffset, y + (3 * height) / 4 + yOffset);
    g2d.drawString(east,
            x + (3 * width) / 4 - eastOffset, y + height / 2 + yOffset);
    g2d.drawString(west,
            x + (width / 4) - westOffset, y + height / 2 + yOffset);
    g2d.setColor(brush);
  }


  // draws the board (in the range 200-1200 x and the entire y)
  private void drawBoard(Graphics2D g2d) {
    Color brush = g2d.getColor();
    int slotWidth = (this.getPreferredSize().width -
            (2 * this.handWidth)) / this.model.getBoardWidth();
    int slotHeight = (this.getPreferredSize().height) / this.model.getBoardHeight();

    int startY = 0;
    int startX = this.handWidth;

    for (int row = 0; row < this.model.getBoardHeight(); row++) {
      for (int col = 0; col < this.model.getBoardWidth(); col++) {
        if (this.model.hasCard(row, col)) {
          this.setBrushPlayer(g2d, this.model.getPlayerAt(row, col));
          this.drawCard(g2d, this.model.getCardAt(row, col), startX + (slotWidth * col),
                  startY + (slotHeight * row), slotWidth, slotHeight);
        } else {
          if (this.model.isHole(row, col)) {
            g2d.setColor(Color.GRAY);

          } else {
            g2d.setColor(Color.YELLOW);
          }
          g2d.fillRect(startX + slotWidth * col, startY + slotHeight * row,
                  slotWidth, slotHeight);
        }
        g2d.setColor(Color.BLACK);
        g2d.drawRect(startX + slotWidth * col, startY + slotHeight * row,
                slotWidth, slotHeight);
      }
    }
    g2d.setColor(brush);
  }

  // sets the color of the brush to the given player
  private void setBrushPlayer(Graphics2D g2d, Player player) {
    switch (player) {
      case Red:
        g2d.setColor(Color.PINK);
        break;
      case Blue:
        g2d.setColor(Color.CYAN);
        break;
      default:
        break;
    }
  }

  /**
   * This method tells Swing what the "natural" size should be
   * for this panel.  Here, we set it to a lot of pixels.
   *
   * @return our preferred *physical* size.
   */
  @Override
  public Dimension getPreferredSize() {
    this.handWidth = this.model.getBoardWidth() * 200;
    return new Dimension(this.model.getBoardWidth() * 1400,
            this.model.getBoardHeight() * 1000);
  }

  //transforms from the logical plane to the physical view plane
  private AffineTransform transformLogicalToPhysical() {
    AffineTransform ret = new AffineTransform();
    Dimension preferred = getPreferredSize();
    double widthScale = (double) getWidth() / (double) preferred.width;
    double heightScale = (double) getHeight() / (double) preferred.height;
    ret.scale(widthScale, heightScale);
    return ret;
  }


}
