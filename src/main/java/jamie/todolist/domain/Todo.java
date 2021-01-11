package jamie.todolist.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Todo {

    @Id @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    public static Todo createTodo(String content) {
        Todo todo = new Todo();
        todo.content = content;
        todo.status = TodoStatus.NEW;

        return todo;
    }

    public void changeStatus(TodoStatus todoStatus) {
        this.status = todoStatus;
    }

}
