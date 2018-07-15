package com.example.todolist

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class JdbcTaskRepository(private val jdbcTamplate: JdbcTemplate) : TaskRepository {
    private val rowMapper = RowMapper<Task> {rs, _ ->
        Task(rs.getLong("id"),rs.getString("content"),rs.getBoolean("done"))
    }

    override fun create(content: String): Task {
        jdbcTamplate.update("INSERT INTO task(content) VALUES(?)", content)
        val id: Long = jdbcTamplate.queryForObject("SELECT last_insert_id()")
        return Task(id, content, false)
    }

    override fun update(task: Task) {
        jdbcTamplate.update("UPDATE task SET content = ?, done = ? WHERE id = ?",
                task.content,
                task.done,
                task.id)
    }

    override fun findAll(): List<Task> =
        jdbcTamplate.query("SELECT id, content, done FROM task", rowMapper)

    override fun findById(id: Long): Task? =
        jdbcTamplate.query("SELECT id, content, done FROM task WHERE id = ?", rowMapper, id).firstOrNull()

}