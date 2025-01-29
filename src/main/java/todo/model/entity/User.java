package todo.model.entity;

public class User {
    public int id;
    public String username;
    public String password;
    public String email;
    public boolean isPremium;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
