package med.voll.api.infra.exception.consulta;

import med.voll.api.dto.CancelamentoConsultaDto;

public interface ValidadorCancelamentoDeConsulta {
    
    void validar(CancelamentoConsultaDto dados);
}
