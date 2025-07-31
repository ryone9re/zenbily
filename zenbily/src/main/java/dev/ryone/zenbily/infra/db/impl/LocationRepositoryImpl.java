package dev.ryone.zenbily.infra.db.impl;

import dev.ryone.zenbily.core.domain.model.Location;
import dev.ryone.zenbily.core.domain.primitive.UserId;
import dev.ryone.zenbily.core.domain.repository.LocationRepository;
import dev.ryone.zenbily.infra.db.mapper.LocationMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

    private final LocationMapper locationMapper;

    @Override
    public List<Location> findAll(int limit) {
        var locationEntities = locationMapper.findAll(limit);
        return locationEntities.stream().map(e -> Location.of(UserId.of(e.userId()), e.latitude(), e.longitude()))
                .toList();
    }

    @Override
    public Location findByUserId(UserId userId) {
        var locationEntity = locationMapper.findByUserId(userId.toString());
        return Location.of(UserId.of(locationEntity.userId()), locationEntity.latitude(), locationEntity.longitude());
    }

    @Override
    public void submitLocation(Location location) {
        var locationEntity = locationMapper.findByUserId(location.getUserId().getValue());
        if (locationEntity != null) {
            locationMapper
                    .updateLocation(location.getUserId().getValue(), location.getLatitude(), location.getLongitude());
            return;
        }

        locationMapper.insertLocation(location.getUserId().getValue(), location.getLatitude(), location.getLongitude());
    }
}
