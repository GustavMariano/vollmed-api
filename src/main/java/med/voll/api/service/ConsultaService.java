package med.voll.api.service;

import org.springframework.stereotype.Service;

import med.voll.api.dto.AgendamentoConsultaDto;
import med.voll.api.infra.exception.consulta.ValidacaoException;
import med.voll.api.model.Consulta;
import med.voll.api.model.Medico;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class ConsultaService {
    
    private ConsultaRepository consultaRepository;

    private MedicoRepository medicoRepository;

    private PacienteRepository pacienteRepository;

    public void agendar(AgendamentoConsultaDto dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("id do paciente informado não existe");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("id do médico informado não existe");
        }

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDto dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não é informado");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
