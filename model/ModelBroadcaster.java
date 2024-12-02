package cs3500.threetrios.model;

/**
 * Represents a ReadOnlyModel with the ability to broadcast signals to subscribers.
 */
public interface ModelBroadcaster extends ThreeTriosReadOnlyModel {


  /**
   * Signs up the given feature to listen to the models.
   *
   * @param features the subscriber (listener to the model)
   */
  public void addSubscriber(ModelFeatures features);

}
