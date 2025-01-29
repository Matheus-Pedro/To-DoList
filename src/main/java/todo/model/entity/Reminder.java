package todo.model.entity;

import java.sql.Timestamp;

public class Reminder {
    int id;
    Task task;
    Timestamp reminderDate;
    boolean sent;
}
