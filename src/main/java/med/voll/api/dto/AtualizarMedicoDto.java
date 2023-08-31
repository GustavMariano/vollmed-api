package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedicoDto(

        @NotNull 
        Long id,

        String nome,

        String telefone,

        DadosEnderecoDto endereco) {

}
