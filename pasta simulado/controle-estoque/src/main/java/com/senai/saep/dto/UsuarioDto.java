package com.senai.saep.dto;

import com.senai.saep.enums.Tipo;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDto {
    private Long id;

    @NotBlank(message = "Informe o login")
    private String login;

    @NotBlank(message = "Informe a senha")
    private String senha;

    private Tipo tipo;

    public UsuarioDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
