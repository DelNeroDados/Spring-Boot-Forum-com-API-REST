package com.delnerodados.forumhub.domain.topico;
import com.delnerodados.forumhub.domain.usuario.Usuario;


import jakarta.persistence.ManyToOne;

import com.delnerodados.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private Boolean ativo = true;

    public Topico() {}

    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
    }

    // Getters e setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public Boolean getAtivo() { return ativo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    @ManyToOne
    private Usuario autor;

    public Topico(DadosCadastroTopico dados, Usuario autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = autor;
    }

    public Usuario getAutor() {
        return autor;
    }

}

