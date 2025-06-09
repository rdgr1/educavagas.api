package org.educavagas.api.controller;

import org.educavagas.api.dto.*;
import org.educavagas.api.services.AuthenticationService;
import org.educavagas.api.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {

    private final RegistrationService registration;
    private final AuthenticationService authentication;

    public AutenticacaoController(
            RegistrationService registration,
            AuthenticationService authentication
    ) {
        this.registration   = registration;
        this.authentication = authentication;
    }

    @PostMapping("/cadastro/responsavel")
    public ResponseEntity<ResponsavelDto> cadastrarResponsavel(
            @Valid @RequestBody ResponsavelDto dto
    ) {
        var salvo = registration.registerResponsavel(dto);
        return ResponseEntity.status(201).body(salvo);
    }

    @PostMapping("/cadastro/escola")
    public ResponseEntity<UsuarioEscolaDto> cadastrarEscola(
            @Valid @RequestBody UsuarioEscolaDto dto
    ) {
        var salvo = registration.registerEscola(dto);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/usuario")
    public ResponseEntity<UsuarioDto> usuarioLogado(Authentication auth) {
        var dto = authentication.getCurrentUser(auth);
        return ResponseEntity.ok(dto);
    }
}