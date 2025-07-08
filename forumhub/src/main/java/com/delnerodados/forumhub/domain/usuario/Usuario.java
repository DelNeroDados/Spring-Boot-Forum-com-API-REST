package com.delnerodados.forumhub.domain.usuario;

import jakarta.persistence.*;

@Entity
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    public Usuario() {}

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // getters e setters
    public Long getId() { return id; }
    public String getLogin() { return login; }
    public String getSenha() { return senha; }
    public void setLogin(String login) { this.login = login; }
    public void setSenha(String senha) { this.senha = senha; }
}
