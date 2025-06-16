package com.example.projeto.controller;

import com.example.projeto.service.UsuarioService;
import com.example.projeto.dto.UsuarioResponseDTO;
import com.example.projeto.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.ListarTodosUsuarios());
    }

    @PostMapping
    public ResponseEntity<Map<Object, String>> salvar(@Valid @RequestBody UsuarioDTO dto) {
        usuarioService.salvarUsuario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Usuario cadastrado com sucesso"));
    }

    @PutMapping("/{email}")
    public ResponseEntity<Map<Object, String>> atualizar(@PathVariable String email,
                                                         @Valid @RequestBody UsuarioDTO dto) {
        usuarioService.atualizar(email, dto);
        return ResponseEntity.ok(Map.of("mensagem", "Usuario atualizado com sucesso"));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Map<Object, String>> excluir(@PathVariable String email) {
        usuarioService.excluir(email);
        return ResponseEntity.ok(Map.of("mensagem", "Usuario excluido com sucesso"));
    }
}
