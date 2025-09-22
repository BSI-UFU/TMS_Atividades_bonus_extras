
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

// Enum para o filtro de status
enum TaskStatus {
    PENDING,
    COMPLETED
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kensl
 */
public class Task implements Serializable, Comparable<Task> {
    
    // Necessário para Serialização
    private static final long serialVersionUID = 1L; 

    private String description;
    private TaskStatus status;
    private LocalDateTime creationDate;

    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.PENDING;
        this.creationDate = LocalDateTime.now();
    }

    // Getters e Setters
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setStatus(TaskStatus status) { this.status = status; }

    /**
     * Define a ordenação padrão por data de criação.
     */
    @Override
    public int compareTo(Task other) {
        return this.creationDate.compareTo(other.creationDate);
    }

    /**
     * Métodos equals/hashCode para tratar "tarefas duplicadas".
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        // Consideramos tarefas duplicadas se a descrição for a mesma
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
