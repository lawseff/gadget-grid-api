package io.github.lawseff.gadgets.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "io.github.lawseff.gadgets")
@EnableJpaRepositories("io.github.lawseff.gadgets.persistence")
@EntityScan("io.github.lawseff.gadgets.persistence")
public class GadgetGridApplication {

  public static void main(String[] args) {
    SpringApplication.run(GadgetGridApplication.class, args);
  }

}
