package cs3500.threetrios.model.components;

/**
 * Represents a Cardinal Direction. One of North, South, East, West.
 */
public enum Direction {
  North, South, East, West;


  /**
   * Returns the Direction opposite of the Direction.
   *
   * @return the opposite Direction
   */
  public Direction opposite() {
    switch (this) {
      case North:
        return South;
      case South:
        return North;
      case East:
        return West;
      case West:
        return East;
      default:
        throw new IllegalArgumentException("Invalid Direction");
    }
  }

}
