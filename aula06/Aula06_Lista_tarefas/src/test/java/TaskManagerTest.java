/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kensl
 */
@DisplayName("Testes do Gerenciador de Tarefas")
public class TaskManagerTest {

    private TaskManager manager;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        
        manager = new TaskManager();
        task1 = new Task("Lavar a louça");
        task2 = new Task("Passear com o cachorro");
    }

    @Test
    @DisplayName("Deve adicionar uma tarefa")
    void testAddTask() {
        manager.addTask(task1);
        assertEquals(1, manager.getTasks().size());
        assertTrue(manager.getTasks().contains(task1));
    }

    @Test
    @DisplayName("Não deve adicionar tarefas duplicadas")
    void testAddDuplicateTask() {
        Task task1_duplicate = new Task("Lavar a louça"); // Mesmo nome
        manager.addTask(task1);
        manager.addTask(task1_duplicate); // Tenta adicionar duplicada
        
        assertEquals(1, manager.getTasks().size(), "Não deve adicionar tarefas com a mesma descrição");
    }

    @Test
    @DisplayName("Deve remover uma tarefa")
    void testRemoveTask() {
        manager.addTask(task1);
        manager.addTask(task2);
        
        manager.removeTask(task1);
        
        assertEquals(1, manager.getTasks().size());
        assertFalse(manager.getTasks().contains(task1));
        assertTrue(manager.getTasks().contains(task2));
    }

    @Test
    @DisplayName("Deve marcar uma tarefa como concluída")
    void testMarkTaskAsCompleted() {
        manager.addTask(task1);
        manager.markTaskAsCompleted(task1);
        
        assertEquals(TaskStatus.COMPLETED, task1.getStatus());
    }

    @Test
    @DisplayName("Deve marcar TODAS as tarefas como concluídas")
    void testMarkAllAsCompleted() {
        manager.addTask(task1);
        manager.addTask(task2);
        
        manager.markAllAsCompleted();
        
        assertTrue(manager.getTasks().stream().allMatch(t -> t.getStatus() == TaskStatus.COMPLETED));
    }

    @Test
    @DisplayName("Deve filtrar tarefas por status")
    void testFilterByStatus() {
        manager.addTask(task1); // PENDING
        manager.addTask(task2); // PENDING
        manager.markTaskAsCompleted(task1); // COMPLETED
        
        List<Task> pendingTasks = manager.filterByStatus(TaskStatus.PENDING);
        List<Task> completedTasks = manager.filterByStatus(TaskStatus.COMPLETED);
        
        assertEquals(1, pendingTasks.size());
        assertEquals("Passear com o cachorro", pendingTasks.get(0).getDescription());
        
        assertEquals(1, completedTasks.size());
        assertEquals("Lavar a louça", completedTasks.get(0).getDescription());
    }

    @Test
    @DisplayName("Deve ordenar tarefas por data de criação")
    void testSortByCreationDate() throws InterruptedException {
        // Força uma diferença de tempo na criação
        Task firstTask = new Task("Primeira Tarefa");
        manager.addTask(firstTask);
        
        Thread.sleep(10); // Pausa de 10ms
        
        Task secondTask = new Task("Segunda Tarefa");
        manager.addTask(secondTask);

        // Adiciona fora de ordem
        manager.getTasks().clear();
        manager.addTask(secondTask);
        manager.addTask(firstTask);

        List<Task> sortedTasks = manager.getTasksSortedByCreationDate();
        
        assertEquals("Primeira Tarefa", sortedTasks.get(0).getDescription());
        assertEquals("Segunda Tarefa", sortedTasks.get(1).getDescription());
    }

    @Test
    @DisplayName("Deve salvar e carregar as tarefas em arquivos")
    void testSaveAndLoadTasks(@TempDir Path tempDir) throws IOException, ClassNotFoundException {
        // @TempDir cria um diretório temporário para o teste
        File file = tempDir.resolve("tasks.dat").toFile();
        
        // 1. Configura o primeiro gerenciador e salva
        manager.addTask(task1);
        manager.addTask(task2);
        manager.markTaskAsCompleted(task1);
        manager.saveTasksToFile(file.getAbsolutePath());
        
        // 2. Cria um novo gerenciador
        TaskManager newManager = new TaskManager();
        assertNotEquals(2, newManager.getTasks().size(), "O novo gerenciador deve começar vazio");

        // 3. Carrega os dados
        newManager.loadTasksFromFile(file.getAbsolutePath());
        
        // 4. Verifica se os dados foram carregados corretamente
        assertEquals(2, newManager.getTasks().size());
        assertEquals("Lavar a louça", newManager.getTasks().get(0).getDescription());
        assertEquals(TaskStatus.COMPLETED, newManager.getTasks().get(0).getStatus());
        assertEquals("Passear com o cachorro", newManager.getTasks().get(1).getDescription());
        assertEquals(TaskStatus.PENDING, newManager.getTasks().get(1).getStatus());
    }
}






