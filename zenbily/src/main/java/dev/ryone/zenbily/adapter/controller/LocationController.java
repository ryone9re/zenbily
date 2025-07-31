package dev.ryone.zenbily.adapter.controller;

import dev.ryone.zenbily.core.service.LocationService;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GetLocationResponse> getLocations(GetLocationsRequest request) {
        var locations = locationService.findAll(new LocationService.FindAllInput(Optional.of(request.limit())));
        return locations.stream().map(GetLocationResponse::fromLocationOutput).toList();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public GetLocationResponse getLocation(@PathVariable String userId) {
        var location = locationService.findByUserId(new LocationService.FindByUserIdInput(userId));
        return GetLocationResponse.fromLocationOutput(location);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void postLocation(
            @CookieValue(value = "user_identity") String userIdentity,
            @RequestBody PostLocationRequest request) {
        locationService.submitLocation(
                new LocationService.SubmitLocationInput(userIdentity, request.latitude(), request.longitude()));
    }

    public record GetLocationsRequest(@Min(value = 1) int limit) {

    }

    public record GetLocationResponse(String userId, double latitude, double longitude) {

        public static GetLocationResponse fromLocationOutput(LocationService.LocationOutput output) {
            return new GetLocationResponse(output.userId(), output.latitude(), output.longitude());
        }
    }

    public record PostLocationRequest(double latitude, double longitude) {

    }
}
