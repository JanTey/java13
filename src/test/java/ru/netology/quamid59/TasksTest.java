package ru.netology.quamid59;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {
    @Test
    public void testMatchesReturnsFalseWhenQueryNotInSubtasks() {
        Epic epic = new Epic(1, new String[]{"Subtask 1", "Subtask 2", "Subtask 3"});
        String query = "Subtask 4";
        boolean expected = false;
        boolean actual = epic.matches(query);
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsTrueWhenQueryInSubtasks() {
        String[] subtasks = {"Subtask 1", "Subtask 2", "Subtask 3"};
        Epic epic = new Epic(1, subtasks);
        String query = "Subtask 2";
        boolean expected = true;
        boolean actual = epic.matches(query);
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsTrueWhenQueryInTitle() {
        SimpleTask task = new SimpleTask(1, "Some task");
        String query = "task";
        boolean expected = true; // ожидаемый результат, так как строка запроса содержится в названии задачи
        boolean actual = task.matches(query);
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsFalseWhenQueryNotInTitle() {
        SimpleTask task = new SimpleTask(1, "Some task");
        String query = "other";
        boolean expected = false; // ожидаемый результат, так как строка запроса не содержится в названии задачи
        boolean actual = task.matches(query);
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsTrueWhenQueryInTopic() {
        Meeting meeting = new Meeting(1, "Some topic", "Some project", "2023-04-20T10:00:00Z");
        String query = "topic";
        boolean expected = true; // ожидаемый результат, так как строка запроса содержится в теме встречи
        boolean actual = meeting.matches(query);
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsTrueWhenQueryInProject() {
        Meeting meeting = new Meeting(1, "Some topic", "Some project", "2023-04-20T10:00:00Z");
        String query = "project";
        boolean expected = true; // ожидаемый результат, так как строка запроса содержится в проекте, к которому относится встреча
        boolean actual = meeting.matches(query);
        assertEquals(expected, actual);
    }

    @Test
    public void testMatchesReturnsFalseWhenQueryNotInTopicOrProject() {
        Meeting meeting = new Meeting(1, "Some topic", "Some project", "2023-04-20T10:00:00Z");
        String query = "other";
        boolean expected = false; // ожидаемый результат, так как строка запроса не содержится ни в теме встречи, ни в проекте
        boolean actual = meeting.matches(query);
        assertEquals(expected, actual);
    }

    @Test
    void testEquals() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);
        Object object = new Object();

        // Сравнение объекта с самим собой
        assertTrue(task1.equals(task1));

        // Сравнение объекта с null
        assertFalse(task1.equals(null));

        // Сравнение объекта с объектом другого класса
        assertFalse(task1.equals(object));

        // Сравнение объекта с объектом того же класса
        assertTrue(task1.equals(task2));
        assertFalse(task1.equals(task3));
    }

    @Test
    void shouldReturnId() {
        Task task = new Task(1);
        assertEquals(1, task.getId());
    }

    @Test
    void hashCodeShouldReturnSameValueForEqualTasks() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void hashCodeShouldReturnDifferentValueForDifferentTasks() {
        Task task1 = new Task(1);
        Task task2 = new Task(2);
        assertNotEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void matchesShouldReturnTrueForMatchingQuery() {
        Task task = new Task(1);
        assertTrue(task.matches("1"));
    }

    @Test
    void matchesShouldReturnFalseForNonMatchingQuery() {
        Task task = new Task(1);
        assertFalse(task.matches("2"));
    }

    @Test
    public void testGetTitle() {
        SimpleTask task = new SimpleTask(1, "Task title");
        assertEquals("Task title", task.getTitle());
    }

    @Test
    public void testConstructorWithoutTitle() {
        SimpleTask task = new SimpleTask(1);
        assertNull(task.getTitle());
    }

    @Test
    public void shouldReturnCorrectTopic() {
        Meeting meeting = new Meeting(1, "Standup meeting", "Project A", "2023-05-05 09:00:00");
        assertEquals("Standup meeting", meeting.getTopic());
    }

    @Test
    public void shouldReturnCorrectProject() {
        Meeting meeting = new Meeting(1, "Standup meeting", "Project A", "2023-05-05 09:00:00");
        assertEquals("Project A", meeting.getProject());
    }

    @Test
    public void shouldReturnCorrectStart() {
        Meeting meeting = new Meeting(1, "Standup meeting", "Project A", "2023-05-05 09:00:00");
        assertEquals("2023-05-05 09:00:00", meeting.getStart());
    }

    @Test
    void shouldReturnSubtasks() {
        String[] subtasks = {"Subtask 1", "Subtask 2", "Subtask 3"};
        Epic epic = new Epic(1, subtasks);
        assertArrayEquals(subtasks, epic.getSubtasks());
    }

    @Test
    void shouldReturnEmptySubtasks() {
        String[] subtasks = {};
        Epic epic = new Epic(1, subtasks);
        assertArrayEquals(subtasks, epic.getSubtasks());
    }

}
