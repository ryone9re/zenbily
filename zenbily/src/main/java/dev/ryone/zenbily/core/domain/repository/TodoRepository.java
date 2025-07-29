package dev.ryone.zenbily.core.domain.repository;

import dev.ryone.zenbily.core.domain.model.Todo;
import dev.ryone.zenbily.core.domain.primitive.TodoId;
import java.util.List;

public interface TodoRepository {

    List<Todo> findAll(int offset, int limit);

    Todo findById(TodoId id);
}
