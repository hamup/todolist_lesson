package com.example.todolist

import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.Size

class TaskCreateForm {

    //入力必須項目
    @NotBlank
    //20文字以内に制限
    @Size(max = 20)
    var content: String? = null
}