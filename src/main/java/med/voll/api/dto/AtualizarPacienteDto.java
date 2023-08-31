package med.voll.api.dto;

import jakarta.validation.Valid;

public record AtualizarPacienteDto(

        Long id,

        String nome,

        String telefone,

        @Valid
        DadosEnderecoDto endereco) {

}
