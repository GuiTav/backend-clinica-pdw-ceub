package ads.pwe.repository;

import java.util.List;

import ads.pwe.dto.DadosEspecialidadeReq;
import ads.pwe.model.Especialidade;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EspecialidadeRepository implements PanacheRepositoryBase<Especialidade, Integer> {
    
    public Especialidade salvarEspecialidade(DadosEspecialidadeReq dados) {
        var especialidade = new Especialidade();
        especialidade.setNomeEspecialidade(dados.nomeEspecialidade());
        persist(especialidade);
        return especialidade;
    }

    public Especialidade editarEspecialidade(Integer idEspecialidade, DadosEspecialidadeReq dados) {
        var especialidade = encontrarEspecialidadePorId(idEspecialidade);
        
        especialidade.setNomeEspecialidade(dados.nomeEspecialidade());
        return especialidade;
    }

    public Especialidade encontrarEspecialidadePorId(Integer id) {
        return findByIdOptional(id)
            .orElseThrow(
                () -> new NotFoundException("Especialidade com id " + id + " não encontrado")
            );
    }

    public List<Especialidade> encontrarEspecialidadesPorIds(List<Integer> ids) {
        return ids.stream().map(id -> {
            return encontrarEspecialidadePorId(id);
        }).toList();
    }

}
