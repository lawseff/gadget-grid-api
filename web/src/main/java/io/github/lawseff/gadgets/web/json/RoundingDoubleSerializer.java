package io.github.lawseff.gadgets.web.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Serializes double values: rounded half-up to 4 decimal places, without trailing zeros, as JSON strings.
 * <br>
 * Note, that Jackson applies a {@link JsonSerializer} to a single handled type, so you need separate instances to
 * handle both the primitive and the wrapper types, e.g.
 * <pre>{@code
 * new RoundingDoubleSerializer(double.class);
 * new RoundingDoubleSerializer(Double.class);
 * }</pre>
 */
@RequiredArgsConstructor
public class RoundingDoubleSerializer extends JsonSerializer<Double> {

  private final Class<Double> handledType;

  /**
   * @see RoundingDoubleSerializer
   */
  private static final DecimalFormat FORMAT = new DecimalFormat("#.####");

  @Override
  public void serialize(Double value, JsonGenerator generator, SerializerProvider provider) throws IOException {
    generator.writeString(FORMAT.format(value));
  }

  @Override
  public Class<Double> handledType() {
    return handledType;
  }

}
