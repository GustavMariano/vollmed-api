package med.voll.api.dto;

import java.time.LocalDateTime;

import med.voll.api.model.Consulta;

public record DetalhamentoConsultaDto(
    
    Long id, 
    Long idMedico, 
    Long idPaciente, 
    LocalDateTime data) {

    public DetalhamentoConsultaDto(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
