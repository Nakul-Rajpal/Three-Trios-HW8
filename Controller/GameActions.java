package cs3500.threetrios.controller;

/**
 * Actions that different components can execute throughout the game.
 * The intended order for a turn is select card, then select board slot.
 */
public interface GameActions {

  /**
   * Notifies that a card in hand has been selected.
   * -2 when no card is selected (Or any other negative number).
   *
   * @param cardIndex the index of the card selected in hand
   */
  public void selectedCardInHand(int cardIndex);

  /**
   * Notifies that a board slot has been selected.
   * -2 when no slot is selected (Or any other negative number).
   *
   * @param row the row index (0-based)
   * @param col the row index (0-based)
   */
  public void selectedBoardSlot(int row, int col);
}
