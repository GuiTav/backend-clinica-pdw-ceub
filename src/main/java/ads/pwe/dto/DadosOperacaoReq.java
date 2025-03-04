package ads.pwe.dto;

public record DadosOperacaoReq(
    String nomeOperacao,
    String descricaoOperacao,
    Integer idEspecialidade,
    Integer duracaoMinutosOperacao
) {
    
}
