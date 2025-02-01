package todo.model.service;

public class Email implements IEmail {
    String email;

    public Email(String email){
        this.email = email;
    }

    @Override
    public boolean validateEmail() {
        return ( this.email.indexOf('@') > 0 );
    }

    @Override
    public String getName(){
        return this.email.substring(0, this.email.indexOf('@'));
    }
}
