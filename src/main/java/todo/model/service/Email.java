package todo.model.service;

public class Email implements IEmail {
    String email;

    public Email(String email){
        this.email = email;
    }

    @Override
    public boolean validarEmail() {
        return ( this.email.indexOf('@') > 0 );
    }

    @Override
    public String obterNome(){
        return this.email.substring(0, this.email.indexOf('@'));
    }
}
