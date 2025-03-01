package ads.pwe.repository;

import ads.pwe.dto.DadosProfissionalReq;
import ads.pwe.model.Profissional;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProfissionalRepository implements PanacheRepositoryBase<Profissional, Integer> {
    
    @Inject
    EspecialidadeRepository especialidadeRepository;

    @Transactional
    public Profissional salvarProfissional(DadosProfissionalReq dados) {
        var especialidades = especialidadeRepository.encontrarEspecialidadesPorIds(dados.idsEspecialidades());

        var profissional = new Profissional();
        profissional.setNomeProfissional(dados.nomeProfissional());
        profissional.setEspecialidades(especialidades);
        persist(profissional);
        return profissional;
    }

    @Transactional
    public Profissional editarProfissional(Integer idProfissional, DadosProfissionalReq dados) {
        var profissional = encontrarProfissionalPorId(idProfissional);
        
        var especialidades = especialidadeRepository.encontrarEspecialidadesPorIds(dados.idsEspecialidades());
        
        profissional.setNomeProfissional(dados.nomeProfissional());
        profissional.setEspecialidades(especialidades);
        return profissional;
    }

    public Profissional encontrarProfissionalPorId(Integer id) {
        return findByIdOptional(id)
            .orElseThrow(
                () -> new NotFoundException("Profissional com id " + id + " não encontrado")
            );
    }

}
