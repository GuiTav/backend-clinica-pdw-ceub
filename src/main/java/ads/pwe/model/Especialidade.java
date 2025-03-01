package ads.pwe.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Especialidade {

    @Id
    @GeneratedValue
    private Integer idEspecialidade;
    
    private String nomeEspecialidade;

    @ManyToMany(mappedBy = "especialidades")
    @JsonBackReference
    private List<Profissional> profissionais;
    
}
