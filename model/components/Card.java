package cs3500.threetrios.model.components;

/**
 * Behaviors for a Card in a game of ThreeTrios.
 */
public interface Card {

  /**
   * Returns the AttackValue associated with the given direction.
   *
   * @return the attack value
   * @throws IllegalArgumentException if the dir is null
   */
  AttackValue getAttackValue(Direction dir);

  /**
   * Returns the String associated with the name of this Card.
   *
   * @return the name
   */
  String getName();

  @Override
  public boolean equals(Object other);

  @Override
  public int hashCode();

  /**
   * Returns true if this beats the defender using its given direction.
   * Compared against the defenders opposite direction.
   *
   * @param defender the card defending
   * @param dir      the direction the attacker is attacking from
   * @return true if the attacker wins
   * @throws IllegalArgumentException if either card or the direction is null
   */
  public boolean beatsOther(Card defender, Direction dir) throws IllegalArgumentException;

}
