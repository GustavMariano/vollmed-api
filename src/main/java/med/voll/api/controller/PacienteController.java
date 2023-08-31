package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.AtualizarPacienteDto;
import med.voll.api.dto.CadastroPacienteDto;
import med.voll.api.dto.ListagemPacienteDto;
import med.voll.api.service.PacienteService;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid CadastroPacienteDto dados) {
        pacienteService.cadastrarPaciente(dados);
    }

    @GetMapping
    public Page<ListagemPacienteDto> listarPacientes(
            @PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
        return pacienteService.listarPacientes(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizarPaciente(@RequestBody @Valid AtualizarPacienteDto dados) {
        pacienteService.atualizarPaciente(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id) {
        pacienteService.excluirPaciente(id);
    }
}
