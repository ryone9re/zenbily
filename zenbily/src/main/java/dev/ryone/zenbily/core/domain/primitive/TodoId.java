package dev.ryone.zenbily.core.domain.primitive;

import dev.ryone.zenbily.exception.IllegalUUIDException;
import java.util.UUID;
import lombok.NonNull;
import lombok.Value;

@Value
public class TodoId {

    String id;

    private TodoId(@NonNull String id) {
        this.id = id;
    }

    public static TodoId of(String value) {
        try {
            UUID id = UUID.fromString(value);
            return new TodoId(id.toString());
        } catch (IllegalArgumentException e) {
            throw new IllegalUUIDException(e, value);
        }
    }

    public static TodoId generate() {
        return new TodoId(UUID.randomUUID().toString());
    }
}
