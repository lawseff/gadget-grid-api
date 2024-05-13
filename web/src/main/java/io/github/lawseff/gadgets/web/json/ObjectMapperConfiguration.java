package io.github.lawseff.gadgets.web.json;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfiguration {

  @Bean
  public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
    var builder = new Jackson2ObjectMapperBuilder();

    builder.serializers(
        new RoundingDoubleSerializer(double.class),
        new RoundingDoubleSerializer(Double.class)
    );

    return builder;
  }

}
