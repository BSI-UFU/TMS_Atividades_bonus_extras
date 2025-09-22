
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kensl
 */
public class TaskManager {

    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }
    
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adiciona uma tarefa, se ela não for duplicada.
     */
    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    /**
     * Remove uma tarefa.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Marca uma tarefa específica como concluída.
     */
    public void markTaskAsCompleted(Task task) {
        this.tasks.stream()
                .filter(t -> t.equals(task))
                .findFirst()
                .ifPresent(t -> t.setStatus(TaskStatus.COMPLETED));
    }

    /**
     * Marca TODAS as tarefas como concluídas.
     */
    public void markAllAsCompleted() {
        this.tasks.forEach(t -> t.setStatus(TaskStatus.COMPLETED));
    }

    /**
     * Filtra tarefas por status.
     */
    public List<Task> filterByStatus(TaskStatus status) {
        return this.tasks.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    /**
     * Ordena tarefas por data de criação (usando o Comparable da Task).
     */
    public List<Task> getTasksSortedByCreationDate() {
        return this.tasks.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Salva as tarefas em um arquivo.
     */
    public void saveTasksToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this.tasks);
        }
    }

    /**
     * Carrega as tarefas de um arquivo.
     */
    @SuppressWarnings("unchecked")
    public void loadTasksFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            this.tasks = (List<Task>) ois.readObject();
        }
    }
}
