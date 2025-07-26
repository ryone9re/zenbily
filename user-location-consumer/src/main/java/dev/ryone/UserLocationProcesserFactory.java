package dev.ryone;

import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
public class UserLocationProcesserFactory implements Supplier<UserLocationProcesser> {
  @Override
  public UserLocationProcesser get() {
    return new UserLocationProcesser();
  }
}
