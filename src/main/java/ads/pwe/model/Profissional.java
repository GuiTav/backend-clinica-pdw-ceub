package ads.pwe.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProfissional;

    private String nomeProfissional;

    @ManyToMany
    @JsonManagedReference
    private List<Especialidade> especialidades = new ArrayList<>();

    @OneToMany(mappedBy = "profissional")
    private List<Agendamento> agendamentos = new ArrayList<>();

}