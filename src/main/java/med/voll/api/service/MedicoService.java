package med.voll.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.api.dto.AtualizarMedicoDto;
import med.voll.api.dto.CadastroMedicoDto;
import med.voll.api.dto.ListagemMedicoDto;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;

@Service
public class MedicoService {
    
    @Autowired
    private MedicoRepository medicoRepository;

    public Medico cadastrarMedico(CadastroMedicoDto dados) {
        return medicoRepository.save(new Medico(dados));
    }

    public Page<ListagemMedicoDto> listarMedicos(Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDto::new);
    }

    public Optional<Medico> detalharMedico(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico atualizarMedico(AtualizarMedicoDto dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
        return medicoRepository.save(medico);
    }

    public void excluirMedico(Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }
}
