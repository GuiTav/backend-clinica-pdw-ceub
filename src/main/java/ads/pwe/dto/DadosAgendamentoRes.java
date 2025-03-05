package ads.pwe.dto;

import java.time.LocalDateTime;

import ads.pwe.model.Agendamento;

public record DadosAgendamentoRes(
    Integer idAgendamento,
    DadosPacienteRes paciente,
    DadosOperacaoRes operacao,
    DadosProfissionalRes profissional,
    LocalDateTime dataHoraAgendamento
) {
    
    public DadosAgendamentoRes(Agendamento agendamento) {
        this(
            agendamento.getIdAgendamento(),
            new DadosPacienteRes(agendamento.getPaciente()),
            new DadosOperacaoRes(agendamento.getOperacao()),
            new DadosProfissionalRes(agendamento.getProfissional()),
            agendamento.getDataHoraAgendamento()
        );
    }

}
