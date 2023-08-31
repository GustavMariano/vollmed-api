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
import med.voll.api.dto.AtualizarMedicoDto;
import med.voll.api.dto.CadastroMedicoDto;
import med.voll.api.dto.ListagemMedicoDto;
import med.voll.api.service.MedicoService;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    
    @Autowired
    private MedicoService medicoService;

    @PostMapping
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid CadastroMedicoDto dados) {
        medicoService.cadastrarMedico(dados);
    }

    @GetMapping
    public Page<ListagemMedicoDto> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return medicoService.listarMedicos(paginacao);
    }

    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid AtualizarMedicoDto dados) {
        medicoService.atualizarMedico(dados);    
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id) {
        medicoService.excluirMedico(id);
    }
}
