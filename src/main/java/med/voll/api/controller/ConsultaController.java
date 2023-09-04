package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AgendamentoConsultaDto;
import med.voll.api.dto.CancelamentoConsultaDto;
import med.voll.api.dto.DetalhamentoConsultaDto;
import med.voll.api.service.ConsultaService;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    
    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoConsultaDto> agendar(@RequestBody @Valid AgendamentoConsultaDto dados) {
        var dto = consultaService.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid CancelamentoConsultaDto dados) {
        consultaService.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}

