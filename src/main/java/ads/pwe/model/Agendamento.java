package ads.pwe.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Agendamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgendamento;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Operacao operacao;

    @ManyToOne
    private Profissional profissional;

    private LocalDateTime dataHoraAgendamento;

}
