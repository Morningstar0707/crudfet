package com.example.crudfet.mapper;

import com.example.crudfet.dto.UsuarioRequestDto;
import com.example.crudfet.dto.UsuarioResponseDto;
import com.example.crudfet.entity.Usuario;

public class UsuarioMapper {

    private UsuarioMapper() {
    }

    public static Usuario toEntity(UsuarioRequestDto dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.nombre());
        usuario.setEmail(dto.email());
        usuario.setActivo(dto.activo());
        return usuario;
    }

    public static UsuarioResponseDto toResponseDto(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getActivo()
        );
    }

    public static void updateEntity(Usuario usuario, UsuarioRequestDto dto) {
        usuario.setNombre(dto.nombre());
        usuario.setEmail(dto.email());
        usuario.setActivo(dto.activo());
    }
}

