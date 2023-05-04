package ru.netology.quamid59;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyArrayWhenSearchingWithNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Сделать домашнее задание"}));
        Task[] expected = {};
        Task[] actual = todos.search("Погладить кота");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayOfTasksWhenSearchingWithOneMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Сделать домашнее задание"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко")};
        Task[] actual = todos.search("молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnArrayOfAllTasksWhenSearchingWithEmptyQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить молоко"));
        todos.add(new Epic(2, new String[]{"Сделать домашнее задание"}));
        Task[] expected = {new SimpleTask(1, "Купить молоко"), new Epic(2, new String[]{"Сделать домашнее задание"})};
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddNewTaskToArray() {
        Task[] tasks = {new SimpleTask(1, "Купить молоко"), new Epic(2, new String[]{"Сделать домашнее задание"})};
        Task newTask = new Meeting(3, "Встреча с клиентами", "Офис компании", "15 мая 2023, 10:00");
        Task[] expected = {new SimpleTask(1, "Купить молоко"), new Epic(2, new String[]{"Сделать домашнее задание"}), newTask};
        Task[] actual = new Todos().addToArray(tasks, newTask);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldAddTaskToArrayWithNullTask() {
        Task[] tasks = {new SimpleTask(1, "Купить молоко"), new Epic(2, new String[]{"Сделать домашнее задание"})};
        Task[] expected = tasks.clone();
        Task[] actual = new Todos().addToArray(tasks, null);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnSubtasks() {
        String[] subtasks = {"Subtask 1", "Subtask 2"};
        Epic epic = new Epic(1, subtasks);
        String[] expected = subtasks.clone();
        String[] actual = epic.getSubtasks();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnTopic() {
        Meeting meeting = new Meeting(1, "Project review", "Project X", "2023-05-10T14:30:00");
        String expected = "Project review";
        String actual = meeting.getTopic();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnProject() {
        Meeting meeting = new Meeting(1, "Project review", "Project X", "2023-05-10T14:30:00");
        String expected = "Project X";
        String actual = meeting.getProject();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnStart() {
        Meeting meeting = new Meeting(1, "Project review", "Project X", "2023-05-10T14:30:00");
        String expected = "2023-05-10T14:30:00";
        String actual = meeting.getStart();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTrueWhenQueryMatchesTopic() {
        Meeting meeting = new Meeting(1, "Project review", "Project X", "2023-05-10T14:30:00");
        boolean actual = meeting.matches("review");
        assertTrue(actual);
    }

    @Test
    public void shouldReturnTrueWhenQueryMatchesProject() {
        Meeting meeting = new Meeting(1, "Project review", "Project X", "2023-05-10T14:30:00");
        boolean actual = meeting.matches("Project X");
        assertTrue(actual);
    }

    @Test
    public void shouldReturnFalseWhenQueryDoesNotMatch() {
        Meeting meeting = new Meeting(1, "Project review", "Project X", "2023-05-10T14:30:00");
        boolean actual = meeting.matches("meeting");
        assertFalse(actual);
    }

    @Test
    public void shouldCreateSimpleTaskWithGivenIdAndEmptyTitle() {
        SimpleTask task = new SimpleTask(1);
        Assertions.assertEquals(1, task.getId());
    }

    @Test
    public void shouldReturnTitleOfSimpleTask() {
        SimpleTask task = new SimpleTask(1, "Buy milk");
        Assertions.assertEquals("Buy milk", task.getTitle());
    }

    @Test
    public void testHashCode() {
        Task task1 = new SimpleTask(1, "Task 1");
        Task task2 = new SimpleTask(1, "Task 2");
        Task task3 = new SimpleTask(2, "Task 3");

        assertEquals(task1.hashCode(), task2.hashCode()); // объекты равны, hashcode должен быть одинаковым
        assertNotEquals(task1.hashCode(), task3.hashCode()); // объекты не равны, hashcode должен быть разным
    }

    @Test
    void shouldReturnFalseForNonMatchingQuery() {
        Task task = new Task(1);
        assertFalse(task.matches("search"));
    }

    @Test
    void shouldReturnTrueForMatchingQuery() {
        Task task = new Task(1);
        assertTrue(task.matches("1"));
    }
}
