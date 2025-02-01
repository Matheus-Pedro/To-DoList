package todo.model.entity;

public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private boolean isPremium;

    public User(int id, String name, String password, String email, boolean isPremium) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.isPremium = isPremium;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium;
    }
}
