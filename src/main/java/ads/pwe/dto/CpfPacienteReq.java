package ads.pwe.dto;

import jakarta.validation.constraints.Pattern;

public record CpfPacienteReq(
    @Pattern(
        regexp = "^([0-9]){3}\\.([0-9]){3}\\.([0-9]){3}-([0-9]){2}$",
        message = "CPF no formato inválido"
    )
    String cpf
) {
    
}
