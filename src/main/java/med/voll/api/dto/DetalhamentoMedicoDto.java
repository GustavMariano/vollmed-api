package med.voll.api.dto;

import med.voll.api.enums.Especialidade;
import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;

public record DetalhamentoMedicoDto(
    
    Long id, 
    String nome, 
    String email, 
    String crm, 
    String telefone, 
    Especialidade especialidade, 
    Endereco endereco) {
    
    public DetalhamentoMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}
