package io.github.lawseff.gadgets.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Dimensions {

  @Column(name = "length_mm")
  private double lengthMillimeters;

  @Column(name = "width_mm")
  private double widthMillimeters;

  @Column(name = "height_mm")
  private double heightMillimeters;

}
