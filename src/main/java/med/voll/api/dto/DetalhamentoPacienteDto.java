package med.voll.api.dto;

import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record DetalhamentoPacienteDto(
    
    String nome, 
    String email, 
    String telefone, 
    String cpf, 
    Endereco endereco) { 
        
    public DetalhamentoPacienteDto(Paciente paciente) { 
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco()); 
    }
} 
