package dev.ryone.config;

import static org.junit.jupiter.api.Assertions.*;

import dev.ryone.UserLocationProcesserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DecatonConfigTest {

  @Autowired
  private DecatonConfig decatonConfig;

  @Autowired
  private UserLocationProcesserFactory processorFactory;

  @Test
  void decatonConfigShouldBeCreated() {
    assertNotNull(decatonConfig);
  }

  @Test
  void userLocationProcesserFactoryShouldBeCreated() {
    assertNotNull(processorFactory);
  }

  @Test
  void processorFactoryShouldProvideValidProcessor() {
    assertNotNull(processorFactory.get());
  }

  @Test
  void logConfigurationShouldNotThrow() {
    assertDoesNotThrow(() -> decatonConfig.logConfiguration());
  }
}
