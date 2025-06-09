// src/main/java/org/educavagas/api/controller/AutenticacaoController.java
package org.educavagas.api.controller;

import org.educavagas.api.dto.*;
import org.educavagas.api.services.AuthenticationService;
import org.educavagas.api.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.*;
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
            @Valid @RequestBody RegisterResponsavelRequest req
    ) {
        var salvo = registration.registerResponsavel(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/cadastro/escola")
    public ResponseEntity<UsuarioEscolaDto> cadastrarEscola(
            @Valid @RequestBody RegisterEscolaRequest req
    ) {
        var salvo = registration.registerEscola(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/usuario")
    public ResponseEntity<UsuarioDto> usuarioLogado(Authentication auth) {
        var dto = authentication.getCurrentUser(auth);
        return ResponseEntity.ok(dto);
    }
}