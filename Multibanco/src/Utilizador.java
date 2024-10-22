public class Utilizador {
  private String nome;
  private String email; 
  private String morada;
  private String telefone;
  private String nif;
  private String login;
  private String pass;

    public Utilizador() {
    }

    public Utilizador(String nome, String email, String morada, String telefone, String nif, String login, String pass) {
        this.nome = nome;
        this.email = email;
        this.morada = morada;
        this.telefone = telefone;
        this.nif = nif;
        this.login = login;
        this.pass = pass;
    }
  //métodos get e set

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    //método de escrita de toos os dados de um utilizador
    public String dadosUtilizador(){
        return "Nome: "+this.nome+"\nEmail: "+this.email+"\nMorada: "+this.morada+"\nTelefone :"
                +this.telefone+"\nNIF: "+this.nif+"\nLogin: "+this.login+"\nPassword: "+this.pass;
    }
}