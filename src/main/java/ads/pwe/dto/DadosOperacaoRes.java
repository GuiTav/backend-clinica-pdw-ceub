package ads.pwe.dto;

import ads.pwe.model.Operacao;

public record DadosOperacaoRes(
    Integer idOperacao,
    String nomeOperacao,    
    String descricaoOperacao,
    Integer duracaoMinutosOperacao,
    DadosEspecialidadeRes especialidade
) {

    public DadosOperacaoRes(Operacao operacao) {
        this(
            operacao.getIdOperacao(),
            operacao.getNomeOperacao(),
            operacao.getDescricaoOperacao(),
            operacao.getDuracaoMinutosOperacao(),
            new DadosEspecialidadeRes(operacao.getEspecialidade())
        );
    }
    
}
