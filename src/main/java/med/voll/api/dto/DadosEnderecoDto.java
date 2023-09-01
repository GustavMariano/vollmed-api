package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoDto(

        @NotBlank(message = "Logradouro é obrigatório")
        String logradouro,

        @NotBlank(message = "Bairro é obrigatório")
        String bairro,

        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{8}", message = "Formato inválido")
        String cep,

        @NotBlank(message = "Cidade é obrigatório")
        String cidade,

        @NotBlank(message = "UF é obrigatório")
        String uf,

        String complemento,
        
        String numero) {
}
