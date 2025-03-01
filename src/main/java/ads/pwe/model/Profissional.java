package ads.pwe.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Profissional {

    @Id
    @GeneratedValue
    private Integer idProfissional;

    private String nomeProfissional;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Especialidade> especialidades;

}