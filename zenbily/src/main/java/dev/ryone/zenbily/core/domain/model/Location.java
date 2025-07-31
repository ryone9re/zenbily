package dev.ryone.zenbily.core.domain.model;

import dev.ryone.zenbily.core.domain.primitive.UserId;
import lombok.Data;

@Data
public class Location {

    private UserId userId;
    private double latitude;
    private double longitude;

    private Location(UserId userId, double latitude, double longitude) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Location of(UserId userId, double latitude, double longitude) {
        return new Location(userId, latitude, longitude);
    }
}
