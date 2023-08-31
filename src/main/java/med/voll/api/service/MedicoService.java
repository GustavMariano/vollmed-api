package med.voll.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
        return medicoRepository.findAll(paginacao).map(ListagemMedicoDto::new);
    }
}
