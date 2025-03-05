package ads.pwe.dto;

import ads.pwe.model.Paciente;

public record DadosPacienteRes(
    String cpfPaciente,
    String nomePaciente
) {
    
    public DadosPacienteRes(Paciente paciente) {
        this(
            paciente.getCpfPaciente(),
            paciente.getNomePaciente()
        );
    }

}
