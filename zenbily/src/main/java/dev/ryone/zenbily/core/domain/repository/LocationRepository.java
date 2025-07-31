package dev.ryone.zenbily.core.domain.repository;

import dev.ryone.zenbily.core.domain.model.Location;
import dev.ryone.zenbily.core.domain.primitive.UserId;
import java.util.List;

public interface LocationRepository {

    List<Location> findAll(int limit);

    Location findByUserId(UserId userId);

    void submitLocation(Location location);
}
