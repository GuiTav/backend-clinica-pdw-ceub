package ads.pwe.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Pattern;

public record DadosAgendamentoReq(
    @Pattern(
        regexp = "^([0-9]){3}\\.([0-9]){3}\\.([0-9]){3}-([0-9]){2}$",
        message = "CPF no formato inválido"
    )
    String cpfPaciente,
    Integer idOperacao,
    Integer idProfissional,
    LocalDateTime dataHoraAgendamento
) {
    
}
