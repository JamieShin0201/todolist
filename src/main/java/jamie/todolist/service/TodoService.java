package jamie.todolist.service;

import jamie.todolist.domain.Todo;
import jamie.todolist.domain.TodoStatus;
import jamie.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long saveTodo(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional
    public void changeTodoStatus(Long todoId, TodoStatus todoStatus) {
        Todo todo = todoRepository.findOne(todoId);
        todo.changeStatus(todoStatus);
    }


    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Todo findOne(Long todoId) {
        return todoRepository.findOne(todoId);
    }

}
