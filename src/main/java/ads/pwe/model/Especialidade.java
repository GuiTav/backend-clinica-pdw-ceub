package ads.pwe.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidade;
    
    private String nomeEspecialidade;

    @ManyToMany(mappedBy = "especialidades")
    @JsonBackReference
    private List<Profissional> profissionais = new ArrayList<>();

    @OneToMany(mappedBy = "especialidade", cascade = CascadeType.ALL)
    private List<Operacao> operacoes = new ArrayList<>();
    
}
