package jamie.todolist.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class TodoForm {
    
    @NotEmpty(message = "할일을 입력해주세요")
    private String content;

}
