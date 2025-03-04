package ads.pwe.repository;

import ads.pwe.dto.DadosPacienteReq;
import ads.pwe.model.Paciente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PacienteRepository implements PanacheRepositoryBase<Paciente, String> {
    
    @Transactional
    public Paciente salvarPaciente(DadosPacienteReq dados) {
        var paciente = new Paciente();

        paciente.setCpfPaciente(dados.cpfPaciente());
        paciente.setNomePaciente(dados.nomePaciente());
        persist(paciente);
        return paciente;
    }

    @Transactional
    public Paciente editarPaciente(DadosPacienteReq dados) {
        var paciente = encontrarPacientePorCpf(dados.cpfPaciente());
        
        paciente.setNomePaciente(dados.nomePaciente());
        return paciente;
    }

    public Paciente encontrarPacientePorCpf(String cpf) {
        return findByIdOptional(cpf)
            .orElseThrow(
                () -> new NotFoundException("Paciente com cpf " + cpf + " não encontrado")
            );
    }
    
}
