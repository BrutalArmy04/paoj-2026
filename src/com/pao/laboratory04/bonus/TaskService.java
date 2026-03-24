package com.pao.laboratory04.bonus;

import java.util.*;

public class TaskService {
    private static TaskService instance;

    private Map<String, Task> tasksById;
    private Map<Priority, List<Task>> tasksByPriority;
    private List<String> auditLog;
    private int idCounter;

    private TaskService() {
        tasksById = new HashMap<>();
        tasksByPriority = new HashMap<>();
        auditLog = new ArrayList<>();
        idCounter = 1;

        for (Priority p : Priority.values()) {
            tasksByPriority.put(p, new ArrayList<>());
        }
    }

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }

    public Task addTask(String title, Priority priority) {
        String id = String.format("T%03d", idCounter++);
        
        if (tasksById.containsKey(id)) {
            throw new DuplicateTaskException("Task-ul cu ID " + id + " exista deja.");
        }

        Task task = new Task(id, title, priority);
        
        tasksById.put(id, task);
        tasksByPriority.get(priority).add(task);
        
        auditLog.add(String.format("[ADD] %s: '%s' (%s)", id, title, priority));
        
        return task;
    }

    public void assignTask(String taskId, String assignee) {
        if (!tasksById.containsKey(taskId)) {
            throw new TaskNotFoundException("Task-ul '" + taskId + "' nu a fost gasit.");
        }
        Task task = tasksById.get(taskId);
        task.setAssignee(assignee);
        auditLog.add(String.format("[ASSIGN] %s → %s", taskId, assignee));
    }

    public void changeStatus(String taskId, Status newStatus) {
        if (!tasksById.containsKey(taskId)) {
            throw new TaskNotFoundException("Task-ul '" + taskId + "' nu a fost gasit.");
        }
        
        Task task = tasksById.get(taskId);
        Status currentStatus = task.getStatus();

        if (!currentStatus.canTransitionTo(newStatus)) {
            throw new InvalidTransitionException(currentStatus, newStatus);
        }

        task.setStatus(newStatus);
        auditLog.add(String.format("[STATUS] %s: %s → %s", taskId, currentStatus, newStatus));
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return tasksByPriority.getOrDefault(priority, new ArrayList<>());
    }

    public Map<Status, Long> getStatusSummary() {
        Map<Status, Long> summary = new HashMap<>();
        // Inițializăm cu 0
        for (Status s : Status.values()) {
            summary.put(s, 0L);
        }
        for (Task t : tasksById.values()) {
            summary.put(t.getStatus(), summary.get(t.getStatus()) + 1);
        }
        return summary;
    }

    public List<Task> getUnassignedTasks() {
        List<Task> unassigned = new ArrayList<>();
        for (Task t : tasksById.values()) {
            if (t.getAssignee() == null) {
                unassigned.add(t);
            }
        }
        return unassigned;
    }

    public double getTotalUrgencyScore(int baseDays) {
        double totalScore = 0;
        for (Task t : tasksById.values()) {
            if (t.getStatus() != Status.DONE && t.getStatus() != Status.CANCELLED) {
                totalScore += t.getPriority().calculateScore(baseDays);
            }
        }
        return totalScore;
    }

    public void printAuditLog() {
        for (String log : auditLog) {
            System.out.println(log);
        }
    }
}