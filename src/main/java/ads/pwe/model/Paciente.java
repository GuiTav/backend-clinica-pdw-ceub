package ads.pwe.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Paciente {

    @Id
    @Pattern(
        regexp = "^([0-9]){3}\\.([0-9]){3}\\.([0-9]){3}-([0-9]){2}$",
        message = "CPF no formato inválido"
    )
    private String cpfPaciente;

    private String nomePaciente;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos = new ArrayList<>();
    
}
