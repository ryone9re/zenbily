package dev.ryone.zenbily.core.service;

import dev.ryone.zenbily.core.domain.model.Location;
import dev.ryone.zenbily.core.domain.primitive.UserId;
import dev.ryone.zenbily.core.domain.repository.LocationRepository;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private static final int DEFAULT_FIND_LOCATION_LIMIT = 1000;

    private final LocationRepository locationRepository;

    @Transactional
    public List<LocationOutput> findAll(FindAllInput input) {
        List<Location> locations = locationRepository.findAll(input.limit.orElse(DEFAULT_FIND_LOCATION_LIMIT));
        return locations.stream().map(LocationOutput::fromLocation).toList();
    }

    @Transactional
    public LocationOutput findByUserId(FindByUserIdInput input) {
        Location location = locationRepository.findByUserId(UserId.of(input.userId()));
        return LocationOutput.fromLocation(location);
    }

    @Transactional
    public void submitLocation(SubmitLocationInput input) {
        Location location = Location.of(UserId.of(input.userId()), input.latitude(), input.longitude());
        locationRepository.submitLocation(location);
    }

    public record FindAllInput(@Min(value = 0) Optional<Integer> limit) {

    }

    public record LocationOutput(String userId, double latitude, double longitude) {

        private static LocationOutput fromLocation(Location location) {
            return new LocationOutput(location.getUserId().getValue(), location.getLatitude(), location.getLongitude());
        }
    }

    public record FindByUserIdInput(@NotNull String userId) {

    }

    public record SubmitLocationInput(String userId, double latitude, double longitude) {

    }
}
