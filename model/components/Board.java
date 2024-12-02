package cs3500.threetrios.model.components;

/**
 * Behaviors for a Board in the game of ThreeTrios.
 */
public interface Board {

  /**
   * Places the given Card onto the Board at the specified row and col (0 indexed).
   *
   * @param card the Card being placed
   * @param row  the row
   * @param col  the col
   * @throws IllegalArgumentException if the card is null
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   * @throws IllegalStateException    if the space is a hole or already has a Card
   */
  public void placeCard(Card card, int row, int col) throws IllegalArgumentException;


  /**
   * Determines if a spot on the Board is a hole.
   *
   * @param row the row
   * @param col the col
   * @return true is the specified spot is a hole
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   */
  public boolean isHole(int row, int col) throws IllegalArgumentException;

  /**
   * Determines if a spot on the Board has a Card.
   *
   * @param row the row
   * @param col the col
   * @return true is the specified spot has a card on it
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   * @throws IllegalStateException    if the specified spot is a hole
   */
  public boolean hasCard(int row, int col) throws IllegalArgumentException, IllegalStateException;

  /**
   * Returns the Card at a given space on the Board (0 indexed).
   *
   * @param row the row
   * @param col the col
   * @return the Card at the given space
   * @throws IllegalStateException    if the space is a hole, or does not have a Card
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   */
  public Card getCard(int row, int col) throws IllegalArgumentException;

  /**
   * Returns the number of Available Card slots on the Board.
   * An Available Card slot is a space on the Board that is not a hole and does not already
   * have a Card.
   *
   * @return the number of Card slots on the Board (int)
   */
  public int getNumberOfAvailableCardSlots();

  /**
   * Returns the number of Total Card slots on the Board.
   * An Available Card slot is a space on the Board that is not a hole
   *
   * @return the number of Card slots on the Board (int)
   */
  public int getTotalCardSlots();


  /**
   * Returns the row of the specified card.
   *
   * @param card The card we're inquiring about
   * @return the row(0 indexed) of the specified card
   * @throws IllegalArgumentException if the card is null
   * @throws IllegalStateException    if the card is not on the board
   */
  public int getRow(Card card);

  /**
   * Returns the col of the specified card.
   *
   * @param card the card we're inquiring about
   * @return the column (0 indexed) of the specified card
   * @throws IllegalArgumentException if the card is null
   * @throws IllegalStateException    if the card is not on the board
   */
  public int getCol(Card card);

  /**
   * Returns the Player who owns the specific spot on the board.
   *
   * @param row the row
   * @param col the col
   * @return the card at the space
   * @throws IllegalStateException    if the space is a hole, or does not have a Card
   * @throws IllegalStateException    if the game has not started
   * @throws IllegalArgumentException if the row is <0 or greater than the width of the Board
   * @throws IllegalArgumentException if the col is <0 or greater than the height of the Board
   */
  public Player getPlayer(int row, int col) throws IllegalArgumentException, IllegalStateException;

  /**
   * Sets the specified spot on the Board to be owned by the given Player.
   *
   * @param player the player
   * @param row    the row
   * @param col    the col
   * @throws IllegalArgumentException if the player is null
   */
  public void setPlayer(Player player, int row, int col)
          throws IllegalArgumentException;


  /**
   * Returns the width of the Board.
   *
   * @return the width of the Board
   */
  public int width();

  /**
   * Returns the height of the Board.
   *
   * @return the height of the Board
   */
  public int height();
}
