package com.example.crudfet.service.impl;

import com.example.crudfet.dto.UsuarioRequestDto;
import com.example.crudfet.dto.UsuarioResponseDto;
import com.example.crudfet.entity.Usuario;
import com.example.crudfet.exception.RecursoNoEncontradoException;
import com.example.crudfet.mapper.UsuarioMapper;
import com.example.crudfet.repository.UsuarioRepository;
import com.example.crudfet.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponseDto crear(UsuarioRequestDto dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioMapper.toResponseDto(guardado);
    }

    @Override
    public List<UsuarioResponseDto> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponseDto)
                .toList();
    }

    @Override
    public UsuarioResponseDto buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toResponseDto)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Usuario no encontrado con id: " + id
                ));
    }

    @Override
    public UsuarioResponseDto actualizar(Long id, UsuarioRequestDto dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Usuario no encontrado con id: " + id
                ));

        UsuarioMapper.updateEntity(usuario, dto);
        Usuario actualizado = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponseDto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Usuario no encontrado con id: " + id
                ));

        usuarioRepository.delete(usuario);
    }
}

