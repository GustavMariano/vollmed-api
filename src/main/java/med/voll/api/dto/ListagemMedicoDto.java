package med.voll.api.dto;

import med.voll.api.enums.Especialidade;
import med.voll.api.model.Medico;

public record ListagemMedicoDto(String nome, String email, String crm, Especialidade especialidade) {
    
    public ListagemMedicoDto(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
