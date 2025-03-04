package ads.pwe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Operacao {
    
    @Id
    @GeneratedValue
    private Integer idOperacao;

    private String nomeOperacao;
    
    private String descricaoOperacao;

    private Integer duracaoMinutosOperacao;

    @ManyToOne
    private Especialidade especialidade;

}
