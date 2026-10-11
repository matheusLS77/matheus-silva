package com.senai.saep.sessao;

import com.senai.saep.enums.Tipo;

public class SessaoDto {
    private Long usuarioId;
    private String usuarioLogin;
    private Tipo usuarioTipo;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public Tipo getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(Tipo usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }
}
