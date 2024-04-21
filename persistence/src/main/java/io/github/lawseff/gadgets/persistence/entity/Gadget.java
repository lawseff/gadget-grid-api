package io.github.lawseff.gadgets.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity(name = "gadget")
@Getter
@Setter
public class Gadget {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "uuid", name = "id")
  private UUID id;

  @Column(name = "name_en")
  private String name;

  @Embedded
  private Dimensions dimensions;

}
