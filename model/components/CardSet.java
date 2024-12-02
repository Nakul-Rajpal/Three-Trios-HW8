package cs3500.threetrios.model.components;

import java.util.List;

/**
 * Behaviors for a set of cards in the game.
 */
public interface CardSet {

  /**
   * Returns the number of cards in the set.
   *
   * @return : the number of cards in the set
   */
  public int size();

  /**
   * Gets the item at the index.
   *
   * @param index : int the index(0 indexed) of the item desired
   * @return the card at that index
   * @throws IllegalArgumentException : if the index is less than zero or greater than the
   *                                  size of the CardSet
   */
  public Card get(int index) throws IllegalArgumentException;

  /**
   * Adds a card into the set.
   *
   * @param card the card to be added
   * @throws IllegalArgumentException if the card is null
   */
  public void addCard(Card card) throws IllegalArgumentException;

  /**
   * Removes a card from the set.
   *
   * @param card the card to be removed
   * @throws IllegalArgumentException if the card is null
   * @throws IllegalStateException    if the hand is empty
   */
  public void removeCard(Card card) throws IllegalArgumentException, IllegalStateException;

  /**
   * List's the cards in the pile.
   *
   * @return a copy of the list of cards in the deck (that changing doesn't affect the set)
   */
  public List<Card> listCards();


}
