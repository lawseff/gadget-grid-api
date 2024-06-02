package io.github.lawseff.gadgets.persistence.test;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
// The Hikari Pool needs to be recreated, because the container is recreated. Otherwise, next tests
// try to access the already stopped container: https://stackoverflow.com/a/65952397
@DirtiesContext
public class DatabaseTest {

  @Container
  @ServiceConnection
  private static final PostgreSQLContainer<?> POSTGRES_TEST_CONTAINER = new PostgreSQLContainer<>(
      System.getProperty("test.postgresql.image")
  );

}
