package todo.model.entity;

import java.util.Date;

public class Task {
    int id;
    String title;
    String description;
    Date dueDate;
    String status;
    User user;
    Category category_id;

    public int getId(){
        return  this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

}
