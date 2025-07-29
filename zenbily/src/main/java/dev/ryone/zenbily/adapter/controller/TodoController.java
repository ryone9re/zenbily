package dev.ryone.zenbily.adapter.controller;

import dev.ryone.zenbily.core.service.TodoService;
import dev.ryone.zenbily.core.service.TodoService.FindAllInput;
import dev.ryone.zenbily.core.service.TodoService.FindByIdInput;
import dev.ryone.zenbily.core.service.TodoService.TodoOutput;
import dev.ryone.zenbily.exception.IllegalUUIDException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
public class TodoController {

    TodoService todoService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<GetTodoResponse> getTodos(@ModelAttribute @Validated GetTodosRequest request) {
        return todoService.findAllTodos(new FindAllInput(request.offset, request.limit)).stream()
                .map(GetTodoResponse::fromTodoOutput).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetTodoResponse getTodo(@PathVariable String id) {
        return GetTodoResponse.fromTodoOutput(todoService.findById(new FindByIdInput(id)));
    }

    public record GetTodosRequest(@NonNull @Min(value = 0) Integer offset,
                                  @NotNull @Min(value = 1) @Max(value = 100) Integer limit) {

    }

    public record GetTodoResponse(String id) {

        public static GetTodoResponse fromTodoOutput(TodoOutput output) {
            return new GetTodoResponse(output.id());
        }
    }


    @ControllerAdvice
    public static class TodoExceptionHandler {

        @ExceptionHandler(IllegalUUIDException.class)
        public ResponseEntity<ErrorResponse> handleIllegalUUIDValue(IllegalUUIDException e) {
            return ResponseEntity.badRequest()
                    .body(new ErrorResponse("E-01", String.format("Cause by %s: %s", e.getCauseValue(), e.getCause())));
        }

        public record ErrorResponse(String code, String message) {

        }
    }
}
