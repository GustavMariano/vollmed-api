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

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarMedicoDto;
import med.voll.api.dto.CadastroMedicoDto;
import med.voll.api.dto.DetalhamentoMedicoDto;
import med.voll.api.dto.ListagemMedicoDto;
import med.voll.api.model.Medico;
import med.voll.api.service.MedicoService;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Medico> cadastrarMedico(@RequestBody @Valid CadastroMedicoDto dados, UriComponentsBuilder uriBuilder) {
        var medico = medicoService.cadastrarMedico(dados);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDto>> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = medicoService.listarMedicos(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> detalharMedico(@PathVariable Long id) {
        Optional<Medico> medico = medicoService.detalharMedico(id);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhamentoMedicoDto> atualizarMedico(@RequestBody @Valid AtualizarMedicoDto dados) {
        var medico = medicoService.atualizarMedico(dados);
        return ResponseEntity.ok(new DetalhamentoMedicoDto(medico));    
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirMedico(@PathVariable Long id) {
        medicoService.excluirMedico(id);
        return ResponseEntity.noContent().build();
    }
}
