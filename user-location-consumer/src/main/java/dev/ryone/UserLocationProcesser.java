package dev.ryone;

import com.linecorp.decaton.processor.DecatonProcessor;
import com.linecorp.decaton.processor.ProcessingContext;
import java.util.logging.Logger;
import zenbily.user_location_consumer.UserLocationOuterClass;
import zenbily.user_location_consumer.UserLocationOuterClass.UserLocation;

public class UserLocationProcesser
    implements DecatonProcessor<UserLocationOuterClass.UserLocation> {
  private static final Logger logger = Logger.getLogger(UserLocationProcesser.class.getName());

  @Override
  public void process(ProcessingContext<UserLocation> processingContext, UserLocation userLocation)
      throws InterruptedException {
    logger.info(
        () ->
            String.format(
                "Processing UserLocation<userId: %s, latitude: %f, longitude: %f>",
                userLocation.getUserId(), userLocation.getLatitude(), userLocation.getLongitude()));
  }
}
