package io.github.lawseff.gadgets.persistence;

import io.github.lawseff.gadgets.persistence.entity.Gadget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface GadgetRepository extends JpaRepository<Gadget, UUID> {
}
