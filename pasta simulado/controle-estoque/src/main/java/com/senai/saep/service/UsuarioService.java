package com.senai.saep.service;

import com.senai.saep.dto.UsuarioDto;
import com.senai.saep.entity.UsuarioEntity;
import com.senai.saep.enums.Tipo;
import com.senai.saep.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UsuarioDto realizarLogin(UsuarioDto usuarioDto){
        Optional<UsuarioEntity> usuarioOp = repository.findByLoginAndSenha(usuarioDto.getLogin(), usuarioDto.getSenha());

        UsuarioDto dto = new UsuarioDto();

        if (usuarioOp.isPresent()) {
            dto = paraDto(usuarioOp.get());
        }

        return dto;
    }

    public void cadastrar(UsuarioDto usuarioDto) {
        UsuarioEntity entity = paraEntity(usuarioDto);

        repository.save(entity);
    }

    public void atualizar(UsuarioDto usuarioDto) {
        UsuarioEntity usuario = repository.findById(usuarioDto.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setTipo(usuarioDto.getTipo());

        repository.save(usuario);
    }

    public List<UsuarioDto> listar() {
        List<UsuarioEntity> usuarios = repository.findAll();
        List<UsuarioDto> usuarioDtos = new ArrayList<>();

        for (UsuarioEntity usuario : usuarios) {
            usuarioDtos.add(paraDto(usuario));
        }

        return usuarioDtos;
    }

    private UsuarioDto paraDto(UsuarioEntity entity) {
        UsuarioDto dto = new UsuarioDto();

        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setSenha(entity.getSenha());
        dto.setTipo(entity.getTipo());

        return dto;
    }

    private UsuarioEntity paraEntity(UsuarioDto dto) {
        UsuarioEntity entity = new UsuarioEntity();

        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
        entity.setId(dto.getId());

        entity.setTipo(dto.getTipo());

        if (dto.getTipo() == null) {
            entity.setTipo(Tipo.OPERADOR);
        }

        return entity;
    }
}
