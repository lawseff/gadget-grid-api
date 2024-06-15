package io.github.lawseff.gadgets.database.test;

import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.containers.MinIOContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
// @DirtiesContext, because a container may add properties to the Spring's Environment.
// E.g. with Postgres, it sets the connection properties. If not cleared, the following error
// can happen: https://stackoverflow.com/a/65952397
@DirtiesContext
public abstract class ContainerTest {

  /**
   * Creates a PostgreSQL container. Example usage:
   * <pre>{@code
   * @Container
   * @ServiceConnection
   * private static final PostgreSQLContainer<?> POSTGRES = createPostgresContainer();
   * }</pre>
   * @return A PostgreSQL container instance
   */
  protected static PostgreSQLContainer<?> createPostgresContainer() {
    var image = System.getProperty("test.postgresql.image");
    return new PostgreSQLContainer<>(image);
  }

  /**
   * Creates a MinIO container. Example usage:
   * <pre>{@code
   * @Container
   * private static final MinIOContainer MINIO = createMinioContainer();
   * }</pre>
   * @return A PostgreSQL container instance
   */
  protected static MinIOContainer createMinioContainer() {
    var image = System.getProperty("test.minio.image");
    return new MinIOContainer(image);
  }

}
