/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_fp07.arenaespace.arena.espace1;


/**
 *
 * @author RyanS
 */
public class Utilizador {
    // Atributos da classe Utilizador, que armazenam informações sobre o utilizador
    protected String NomeCompleto; // Nome completo do utilizador
    protected String NomeDeUtilizador; // Nome de utilizador (username)
    protected String email; // Email do utilizador
    protected String password; // Senha do utilizador
    protected String Privilegio; // Nível de privilégio do utilizador (por exemplo, admin, usuário comum)

    // Construtor da classe Utilizador que inicializa os atributos do utilizador
    public Utilizador(String NomeCompleto, String NomeDeUtilizador, String email, String password, String Privilegio) {
        this.NomeCompleto = NomeCompleto; // Inicializa o nome completo do utilizador
        this.NomeDeUtilizador = NomeDeUtilizador; // Inicializa o nome de utilizador
        this.email = email; // Inicializa o email do utilizador
        this.password = password; // Inicializa a senha do utilizador
        this.Privilegio = Privilegio; // Inicializa o privilégio do utilizador
    }
    
    // Método que imprime os dados do utilizador
    public void imprimeDados(){
        System.out.println("Nome: " + this.getNomeCompleto()); // Imprime o nome completo
        System.out.println(this.NomeDeUtilizador); // Imprime o nome de utilizador
        System.out.println(this.email); // Imprime o email
        System.out.println(this.Privilegio); // Imprime o privilégio
        System.out.println("Utilizador cadastrado com sucesso"); // Mensagem de sucesso
    }
    
    // Método getter para obter o nome completo do utilizador
    public String getNomeCompleto() {
        return NomeCompleto; // Retorna o nome completo do utilizador
    }
    
    // Método setter para definir ou alterar o nome completo do utilizador
    public void setNomeCompleto(String NomeCompleto) {
        this.NomeCompleto = NomeCompleto; // Atualiza o nome completo do utilizador
    }

    // Método getter para obter o nome de utilizador
    public String getNomeDeUtilizador() {
        return NomeDeUtilizador; // Retorna o nome de utilizador
    }

    // Método setter para definir ou alterar o nome de utilizador
    public void setNomeDeUtilizador(String NomeDeUtilizador) {
        this.NomeDeUtilizador = NomeDeUtilizador; // Atualiza o nome de utilizador
    }

    // Método getter para obter o email do utilizador
    public String getEmail() {
        return email; // Retorna o email do utilizador
    }

    // Método setter para definir ou alterar o email do utilizador
    public void setEmail(String email) {
        this.email = email; // Atualiza o email do utilizador
    }

    // Método getter para obter a senha do utilizador
    public String getPassword() {
        return password; // Retorna a senha do utilizador
    }
    
    // Método setter para definir ou alterar a senha do utilizador
    public void setPassword(String password) {
        this.password = password; // Atualiza a senha do utilizador
    }

    // Método getter para obter o privilégio do utilizador
    public String getPrivilegio(){
        return Privilegio; // Retorna o privilégio do utilizador
    } 
}
