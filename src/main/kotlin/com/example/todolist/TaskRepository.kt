package com.example.todolist

interface TaskRepository {


    //タスクの作成
    fun create(content: String): Task

    //タスクの更新
    fun update(task: Task)

    //全件取得
    fun findAll(): List<Task>

    //id検索
    fun findById(id: Long): Task?
}