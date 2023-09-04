package med.voll.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarPacienteDto;
import med.voll.api.dto.CadastroPacienteDto;
import med.voll.api.dto.DetalhamentoPacienteDto;
import med.voll.api.dto.ListagemPacienteDto;
import med.voll.api.model.Paciente;
import med.voll.api.service.PacienteService;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public ResponseEntity<Paciente> cadastrarPaciente(@RequestBody @Valid CadastroPacienteDto dados, UriComponentsBuilder uriBuilder) {
        var paciente = pacienteService.cadastrarPaciente(dados);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDto>> listarPacientes(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        var page = pacienteService.listarPacientes(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> detalharPaciente(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteService.detalharPaciente(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoPacienteDto> atualizarPaciente(@RequestBody @Valid AtualizarPacienteDto dados) {
        var paciente = pacienteService.atualizarPaciente(dados);
        return ResponseEntity.ok(new DetalhamentoPacienteDto(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
        return ResponseEntity.noContent().build();
    }
}
