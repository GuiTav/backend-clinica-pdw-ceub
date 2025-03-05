package ads.pwe.dto;

import ads.pwe.model.Especialidade;

public record DadosEspecialidadeRes(
    Integer idEspecialidade,
    String nomeEspecialidade
) {
    
    public DadosEspecialidadeRes(Especialidade especialidade) {
        this(
            especialidade.getIdEspecialidade(),
            especialidade.getNomeEspecialidade()
        );
    }

}
