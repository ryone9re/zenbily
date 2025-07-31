package dev.ryone.zenbily.core.domain.primitive;

import dev.ryone.zenbily.exception.IllegalUserIdException;
import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class UserId {

    String value;

    private UserId(@NonNull String value) {
        this.value = value;
    }

    public static UserId of(String value) {
        try {
            UUID id = UUID.fromString(value);
            return new UserId(id.toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalUserIdException(e, value);
        }
    }

    public static UserId generate() {
        return new UserId(UUID.randomUUID().toString());
    }
}
