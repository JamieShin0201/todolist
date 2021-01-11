package jamie.todolist.controller;

import jamie.todolist.domain.Todo;
import jamie.todolist.domain.TodoStatus;
import jamie.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping("/")
    public String home(Model model) {
        log.info("Todo controller - home");
        model.addAttribute("todoForm", new TodoForm());
        model.addAttribute("todos", todoService.findTodos());
        return "home";
    }

    @PostMapping("/todos/new")
    public String create(@Valid TodoForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("todos", todoService.findTodos());
            return "home";
        }

        Todo todo = Todo.createTodo(form.getContent());

        todoService.saveTodo(todo);
        return "redirect:/";
    }

    @PostMapping("/todos/{todoId}/complete")
    public String complete(@PathVariable("todoId") Long todoId, Model model) {
        todoService.changeTodoStatus(todoId, TodoStatus.COMPLETED);
        return "redirect:/";
    }

    @PostMapping("/todos/{todoId}/delete")
    public String delete(@PathVariable("todoId") Long todoId, Model model) {
        todoService.changeTodoStatus(todoId, TodoStatus.DELETED);
        return "redirect:/";
    }

}
