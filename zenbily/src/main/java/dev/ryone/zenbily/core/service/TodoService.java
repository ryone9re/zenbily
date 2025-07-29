package dev.ryone.zenbily.core.service;

import dev.ryone.zenbily.core.domain.model.Todo;
import dev.ryone.zenbily.core.domain.primitive.TodoId;
import dev.ryone.zenbily.core.domain.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    TodoRepository todoRepository;

    @Transactional
    public List<TodoOutput> findAllTodos(FindAllInput command) {
        List<Todo> todos = todoRepository.findAll(command.limit, command.offset);
        return todos.stream().map(TodoOutput::fromTodo).toList();
    }

    @Transactional
    public TodoOutput findById(FindByIdInput command) {
        Todo todo = todoRepository.findById(TodoId.of(command.id));
        return TodoOutput.fromTodo(todo);
    }

    public record TodoOutput(String id) {

        public static TodoOutput fromTodo(Todo todo) {
            return new TodoOutput(todo.getId().toString());
        }
    }

    public record FindAllInput(int offset, int limit) {

    }

    public record FindByIdInput(String id) {

    }
}
