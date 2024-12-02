package cs3500.threetrios.model;

import java.util.List;

import cs3500.threetrios.model.components.Board;
import cs3500.threetrios.model.components.Card;
import cs3500.threetrios.model.components.Player;

/**
 * Behaviors for a Read Only version of a ThreeTriosModel.
 */
public interface ThreeTriosReadOnlyModel {

  /**
   * Returns the Player who won the game.
   *
   * @return the Player who won or the first player in the event of a tie
   * @throws IllegalStateException if the game has not started or hasn't been won
   */
  public Player getWinner() throws IllegalStateException;

  /**
   * Returns a list of players in the game.
   *
   * @return the list of players in the game, editing this list will not affect the game
   * @throws IllegalStateException if the game has not started
   */
  public List<Player> getAllPlayers() throws IllegalStateException;

  /**
   * Returns the score of the given player. The score is calculated by adding up the number
   * of cards on the board that belong to the given player, and the number of cards in that players
   * hand.
   *
   * @param player the player
   * @return the score of the player
   * @throws IllegalArgumentException if the player is null
   * @throws IllegalStateException    if the game has not started
   */
  public int getPlayerScore(Player player) throws IllegalArgumentException, IllegalStateException;

  /**
   * Determines the number of flipped cards resulting from playing the given Card at the given
   * row and col.
   *
   * @param card the card
   * @param row  the row
   * @param col  the col
   * @return the number of cards that are flipped in the given card is played to the coordinate
   * @throws IllegalStateException    if the game has not started or the game is over
   * @throws IllegalArgumentException if row < 0 or more than the number of rows
   * @throws IllegalArgumentException if col < 0 or more than the number of cols
   * @throws IllegalArgumentException if the card is null
   * @throws IllegalArgumentException if the player is null
   * @throws IllegalStateException    if the specified row and col is a hole or already has a Card
   */
  public int getFlips(Card card, Player player, int row, int col)
          throws IllegalArgumentException, IllegalStateException;


  /**
   * Returns if the game is over as specified by the implementation.
   * (See piazza @873)
   *
   * @return true if the game has ended and false otherwise
   * @throws IllegalStateException if the game has not started
   */
  public boolean isGameOver() throws IllegalStateException;

  /**
   * Returns if the game has been won.
   *
   * @return : true if the game has been won, false if it's a tie, or the game is not over
   * @throws IllegalStateException if the game has not started
   */
  public boolean isGameWon() throws IllegalStateException;

  /**
   * Determines if the specified row and col is legal for a card play.
   *
   * @param row the row
   * @param col the col
   * @return true if a card can be played to the space
   * @throws IllegalStateException if the game has not started or is over
   */
  public boolean isLegalPlay(int row, int col);

  /**
   * Returns a copy of the players hand in the game. This means modifying the returned list
   * or the cards in the list has no effect on the game.
   *
   * @param player the player whose hand we want
   * @return a new list containing the cards in the player's hands in the same order
   *     as in the current state of the game.
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalArgumentException if the player is null
   * @throws IllegalArgumentException if the player is not in the game
   */
  public List<Card> getHand(Player player) throws IllegalStateException, IllegalArgumentException;

  /**
   * Determines if a spot on the Board is a hole.
   *
   * @param row the row
   * @param col the col
   * @return true is the specified spot is a hole
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   */
  public boolean isHole(int row, int col) throws IllegalArgumentException, IllegalStateException;

  /**
   * Determines if a spot on the Board contains a Card.
   *
   * @param row the row
   * @param col the row
   * @return true if the spot contains a Card
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   */
  public boolean hasCard(int row, int col) throws IllegalArgumentException, IllegalStateException;


  /**
   * Returns the Player who owns the specific spot on the board.
   *
   * @param row the row
   * @param col the col
   * @return the card at the space
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalStateException    if the space is a hole, or does not have a Card
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   */
  public Player getPlayerAt(int row, int col)
          throws IllegalArgumentException, IllegalStateException;


  /**
   * Returns the width of the Board. (Number of columns)
   *
   * @return the width of the Board
   * @throws IllegalStateException if the game has not been started
   */
  public int getBoardWidth() throws IllegalStateException;

  /**
   * Returns the height of the Board.(Number of rows)
   *
   * @return the height of the Board
   * @throws IllegalStateException if the game has not been started
   */
  public int getBoardHeight() throws IllegalStateException;

  /**
   * Gets the player whose turn it is as defined by the model.
   *
   * @return the current player whose turn it is as defined by hte model
   * @throws IllegalStateException if the game has not been started
   */
  public Player getCurrentPlayer() throws IllegalStateException;

  /**
   * Returns a copy of the Board.
   *
   * @return a copy of the Board
   * @throws IllegalStateException if the game has not been started
   */
  public Board getCurrentBoard() throws IllegalStateException;


  /**
   * Returns the Card at a given spot on the Board.
   *
   * @param row the row
   * @param col the col
   * @return the card at the space
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalStateException    if the space is a hole, or does not have a Card
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   */
  public Card getCardAt(int row, int col) throws IllegalArgumentException, IllegalStateException;
}
