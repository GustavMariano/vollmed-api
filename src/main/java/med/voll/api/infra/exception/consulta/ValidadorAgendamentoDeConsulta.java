package med.voll.api.infra.exception.consulta;

import med.voll.api.dto.AgendamentoConsultaDto;

public interface ValidadorAgendamentoDeConsulta {
    
    void validar(AgendamentoConsultaDto dados);
}
