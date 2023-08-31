package med.voll.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.dto.AtualizarPacienteDto;
import med.voll.api.dto.CadastroPacienteDto;
import med.voll.api.dto.ListagemPacienteDto;
import med.voll.api.model.Paciente;
import med.voll.api.repository.PacienteRepository;

@Service
public class PacienteService {
    
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(CadastroPacienteDto dados) {
        return pacienteRepository.save(new Paciente(dados));
    }

    public Page<ListagemPacienteDto> listarPacientes(Pageable paginacao) {
        return pacienteRepository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDto::new);
    }

    public Optional<Paciente> detalharPaciente(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente atualizarPaciente(AtualizarPacienteDto dados) {
        var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);
        return pacienteRepository.save(paciente);
    }

    public void excluirPaciente(Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.excluir();
    }
}
