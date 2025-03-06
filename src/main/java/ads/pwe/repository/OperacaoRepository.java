package ads.pwe.repository;

import ads.pwe.dto.DadosOperacaoReq;
import ads.pwe.model.Operacao;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OperacaoRepository implements PanacheRepositoryBase<Operacao, Integer> {
    
    @Inject
    EspecialidadeRepository especialidadeRepository;

    public Operacao salvarOperacao(DadosOperacaoReq dados) {
        var especialidade = especialidadeRepository.encontrarEspecialidadePorId(dados.idEspecialidade());

        var operacao = new Operacao();
        operacao.setNomeOperacao(dados.nomeOperacao());
        operacao.setDescricaoOperacao(dados.descricaoOperacao());
        operacao.setDuracaoMinutosOperacao(dados.duracaoMinutosOperacao());
        operacao.setEspecialidade(especialidade);
        persist(operacao);
        return operacao;
    }

    public Operacao editarOperacao(Integer idOperacao, DadosOperacaoReq dados) {
        var operacao = encontrarOperacaoPorId(idOperacao);
        
        var especialidade = especialidadeRepository.encontrarEspecialidadePorId(dados.idEspecialidade());
        
        operacao.setNomeOperacao(dados.nomeOperacao());
        operacao.setDescricaoOperacao(dados.descricaoOperacao());
        operacao.setDuracaoMinutosOperacao(dados.duracaoMinutosOperacao());
        operacao.setEspecialidade(especialidade);
        return operacao;
    }

    public Operacao encontrarOperacaoPorId(Integer id) {
        return findByIdOptional(id)
            .orElseThrow(
                () -> new NotFoundException("Operacao com id " + id + " não encontrado")
            );
    }

}
