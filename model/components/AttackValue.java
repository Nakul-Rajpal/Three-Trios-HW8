package cs3500.threetrios.model.components;

/**
 * Represents the Attack Value of a Card in Three Trios.
 */
public enum AttackValue {
  One(1),
  Two(2),
  Three(3),
  Four(4),
  Five(5),
  Six(6),
  Seven(7),
  Eight(8),
  Nine(9),
  A(10);

  private final int score;


  AttackValue(int sc) {
    this.score = sc;
  }

  /**
   * Returns an AttackValue enum associated with the given int.
   *
   * @param num the AttackValue num
   * @return corresponding AttackValue enum
   */
  public static AttackValue fromString(String num) {
    switch (num) {
      case "1":
        return AttackValue.One;
      case "2":
        return AttackValue.Two;
      case "3":
        return AttackValue.Three;
      case "4":
        return AttackValue.Four;
      case "5":
        return AttackValue.Five;
      case "6":
        return AttackValue.Six;
      case "7":
        return AttackValue.Seven;
      case "8":
        return AttackValue.Eight;
      case "9":
        return AttackValue.Nine;
      case "A":
        return AttackValue.A;
      default:
        throw new IllegalArgumentException("Invalid AttackValue");
    }

  }

  @Override
  public String toString() {
    if (this == AttackValue.A) {
      return "A";
    }
    return "" + this.score;
  }

  /**
   * Returns the value associated with the Attack Value.
   *
   * @return score: the point value of the Attack Value
   */
  public int getValue() {
    return this.score;
  }
}
