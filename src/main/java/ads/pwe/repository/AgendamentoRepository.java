package ads.pwe.repository;

import java.time.LocalDateTime;

import ads.pwe.dto.DadosAgendamentoReq;
import ads.pwe.model.Agendamento;
import ads.pwe.model.Operacao;
import ads.pwe.model.Paciente;
import ads.pwe.model.Profissional;
import ads.pwe.util.LocalDateTimeUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AgendamentoRepository implements PanacheRepositoryBase<Agendamento, Integer> {
    
    @Inject
    OperacaoRepository operacaoRepository;

    @Inject
    PacienteRepository pacienteRepository;

    @Inject
    ProfissionalRepository profissionalRepository;

    public Agendamento salvarAgendamento(DadosAgendamentoReq dados) {
        var paciente = pacienteRepository.encontrarPacientePorCpf(dados.cpfPaciente());
        var operacao = operacaoRepository.encontrarOperacaoPorId(dados.idOperacao());
        var profissional = profissionalRepository.encontrarProfissionalPorId(dados.idProfissional());
        
        var inicioAgendamento = dados.dataHoraAgendamento();
        var fimAgendamento = inicioAgendamento.plusMinutes(operacao.getDuracaoMinutosOperacao());

        validarPeriodoAgendamento(paciente, profissional, inicioAgendamento, fimAgendamento);
        validarEspecialidade(profissional, operacao);

        var agendamento = new Agendamento();
        agendamento.setPaciente(paciente);
        agendamento.setOperacao(operacao);
        agendamento.setProfissional(profissional);
        agendamento.setDataHoraAgendamento(dados.dataHoraAgendamento());
        persist(agendamento);
        return agendamento;
    }

    public Agendamento editarAgendamento(Integer idAgendamento, DadosAgendamentoReq dados) {
        var agendamento = encontrarAgendamentoPorId(idAgendamento);
        var paciente = pacienteRepository.encontrarPacientePorCpf(dados.cpfPaciente());
        var operacao = operacaoRepository.encontrarOperacaoPorId(dados.idOperacao());
        var profissional = profissionalRepository.encontrarProfissionalPorId(dados.idProfissional());
        
        var inicioAgendamento = dados.dataHoraAgendamento();
        var fimAgendamento = inicioAgendamento.plusMinutes(operacao.getDuracaoMinutosOperacao());

        validarPeriodoAgendamento(paciente, profissional, inicioAgendamento, fimAgendamento);
        validarEspecialidade(profissional, operacao);

        agendamento.setPaciente(paciente);
        agendamento.setOperacao(operacao);
        agendamento.setProfissional(profissional);
        agendamento.setDataHoraAgendamento(dados.dataHoraAgendamento());
        return agendamento;
    }

    public void validarPeriodoAgendamento(
        Paciente paciente,
        Profissional profissional,
        LocalDateTime inicioAgendamento,
        LocalDateTime fimAgendamento
    ) {
        if (inicioAgendamento.isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Não é possível agendar algo no passado");
        }

        var agendamentosPacienteNoPeriodo = paciente.getAgendamentos().stream().filter(agendamento -> {
            return AgendamentoRepository.isAgendamentoEntrePeriodos(agendamento, inicioAgendamento, fimAgendamento);
        }).toList();

        if (!agendamentosPacienteNoPeriodo.isEmpty()) {
            throw new BadRequestException("O paciente já possui agendamentos no período");
        }

        var agendamentosProfissionalNoPeriodo = profissional.getAgendamentos().stream().filter(agendamento -> {
            return AgendamentoRepository.isAgendamentoEntrePeriodos(agendamento, inicioAgendamento, fimAgendamento);
        }).toList();

        if (!agendamentosProfissionalNoPeriodo.isEmpty()) {
            throw new BadRequestException("O profissional já possui agendamentos no período");
        }
    }

    public void validarEspecialidade(Profissional profissional, Operacao operacao) {
        var profissionalPossuiEspecialidade = profissional.getEspecialidades().contains(
            operacao.getEspecialidade()
        );

        if (!profissionalPossuiEspecialidade) {
            throw new BadRequestException(
                "O profissional selecionado não pode executar funções nesta especialidade"
            );
        }
    }

    public static Boolean isAgendamentoEntrePeriodos(
        Agendamento agendamento,
        LocalDateTime inicioPeriodo,
        LocalDateTime fimPeriodo
    ) {
        var inicioAgendamento = agendamento.getDataHoraAgendamento();
        var fimAgendamento = inicioAgendamento.plusMinutes(agendamento.getOperacao().getDuracaoMinutosOperacao());

        var isInicioEntre = LocalDateTimeUtils.isTimestampEntrePeriodos(inicioAgendamento, inicioPeriodo, fimPeriodo);
        var isFimEntre = LocalDateTimeUtils.isTimestampEntrePeriodos(fimAgendamento, inicioPeriodo, fimPeriodo);
        return isInicioEntre || isFimEntre;
    }

    public Agendamento encontrarAgendamentoPorId(Integer id) {
        return findByIdOptional(id)
            .orElseThrow(
                () -> new NotFoundException("Agendamento com id " + id + " não encontrado")
            );
    }

}
