package ads.pwe.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Operacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOperacao;

    private String nomeOperacao;
    
    private String descricaoOperacao;

    private Integer duracaoMinutosOperacao;

    @ManyToOne
    private Especialidade especialidade;

    @OneToMany(mappedBy = "operacao")
    private List<Agendamento> agendamentos;

}
