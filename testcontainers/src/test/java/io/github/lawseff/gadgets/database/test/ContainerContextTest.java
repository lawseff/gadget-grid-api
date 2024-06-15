package io.github.lawseff.gadgets.database.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
@SpringBootApplication
class ContainerContextTest extends ContainerTest {

  @Container
  @ServiceConnection
  private static final PostgreSQLContainer<?> POSTGRES = createPostgresContainer();

  @Container
  private static final MinIOContainer MINIO = createMinioContainer();

  @Test
  void contextLoads() {
  }

}