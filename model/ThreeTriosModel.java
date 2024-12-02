package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.components.Board;
import cs3500.threetrios.model.components.Card;

/**
 * General behaviors for a Model in a game of ThreeTrios.
 */
public interface ThreeTriosModel extends ThreeTriosReadOnlyModel {

  /**
   * Plays the given Card onto the Board at the specified row and col from the hand of the current.
   * turn player.
   *
   * @param handIndex the index of the card in the players hand
   * @param row       a 0-index number representing which row to play to
   * @param col       a 0-index number representing which col to play to
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalArgumentException if row < 0 or more than the number of rows
   * @throws IllegalArgumentException if col < 0 or more than the number of cols
   * @throws IllegalArgumentException if handIndex < 0 or more than the number of cards in hand
   * @throws IllegalStateException    if the specified row and col is a hole or already has a Card
   * @throws IllegalStateException    if you haven't battled since last playing a card
   */
  public void playCard(int handIndex, int row, int col)
          throws IllegalArgumentException, IllegalStateException;


  /**
   * Executes all battles between Cards on the Board.
   * The given Card attacks all cards adjacent to it, and any that are beaten are flipped
   * and then attack all cards adjacent to them (recursive)
   * SIDE EFFECT: flips all losing cards Player to the given Card's player,
   * SIDE EFFECT: updates the current player
   *
   * @throws IllegalStateException if the game has not started or is over
   * @throws IllegalStateException if the card is not from the current turn player
   * @throws IllegalStateException if there hasn't been a card played this turn
   */
  public void doBattle() throws IllegalStateException, IllegalArgumentException;


  /**
   * Starts a game in the default state for the model.
   * The cards are dealt to player's traditionally, with the cards being given to
   * Player 1, then Player 2, and repeating in this pattern until the hands are full
   *
   * @param deck the list of cards for the game
   * @param grid the board for the game
   * @throws IllegalStateException    if the game has started or the game is over
   * @throws IllegalArgumentException if the deck is null
   * @throws IllegalArgumentException if the grid is null
   * @throws IllegalArgumentException if the file is not a valid input for its type
   * @throws IllegalArgumentException if deck's size is not large enough to setup the game
   * @throws IllegalArgumentException if deck has non-unique cards
   * @throws IllegalArgumentException if the grid does not have an odd number of cards
   */
  public void startGame(List<Card> deck, Board grid, boolean shuffle)
          throws IllegalStateException, IllegalArgumentException;

}
