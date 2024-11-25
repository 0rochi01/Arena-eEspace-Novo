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
    protected String NomeCompleto;
    protected String NomeDeUtilizador;
    protected String email;
    protected String password;
    protected String Privilegio;


    public Utilizador(String NomeCompleto, String NomeDeUtilizador, String email, String password, String Privilegio) {
        this.NomeCompleto = NomeCompleto;
        this.NomeDeUtilizador = NomeDeUtilizador;
        this.email = email;
        this.password = password;
        this.Privilegio = Privilegio;
    }
    
    public void imprimeDados(){
        System.out.println("Nome: " + this.getNomeCompleto());
        System.out.println(this.NomeDeUtilizador);
        System.out.println(this.email);
        System.out.println(this.Privilegio);
        System.out.println("Utilizador cadastrado com suceso");
    }
    
    //* permitir que acessem o as variáveis por fora da classe Utilizador, porem, não permito que o valor seja alterado*//
    public String getNomeCompleto() { //* pega o nome completo do utilizador e 
        return NomeCompleto;  //* e retorna p'ra mim este nome     
    }
    
    //* permitir que acessem as variáveis por fora da classe Utilizador e configurem/alterem NomeCompleto do Utilizador*//
    public void setNomeCompleto(String NomeCompleto) {  //* alterar nome completo */
        this.NomeCompleto = NomeCompleto; //* Esse nome completo recebe um novo nome completo */
    }

    public String getNomeDeUtilizador() {
        return NomeDeUtilizador;
    }

    public void setNomeDeUtilizador(String NomeDeUtilizador) {
        this.NomeDeUtilizador = NomeDeUtilizador;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilegio(){
        return Privilegio;
    } 
}
