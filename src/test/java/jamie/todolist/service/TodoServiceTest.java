package jamie.todolist.service;

import jamie.todolist.domain.Todo;
import jamie.todolist.domain.TodoStatus;
import jamie.todolist.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired
    TodoService todoService;

    @Autowired
    TodoRepository todoRepository;

    Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo();
        todo.createTodo("운동하기!");
    }

    @DisplayName("할일 등록")
    @Test
    void addTodo() {
        Long todoId = todoService.saveTodo(todo);

        assertThat(todoService.findOne(todoId)).isEqualTo(todo);
    }

    @DisplayName("할일 상태 변경")
    @ParameterizedTest
    @EnumSource(value = TodoStatus.class, names = {"COMPLETED", "DELETED"})
    void completeTodo(TodoStatus todoStatus) {
        Long todoId = todoService.saveTodo(todo);

        todoService.changeTodoStatus(todoId, todoStatus);

        assertThat(todo.getStatus()).isEqualTo(todoStatus);
    }

}