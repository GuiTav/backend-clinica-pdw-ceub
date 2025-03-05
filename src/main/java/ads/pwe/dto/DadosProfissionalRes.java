package ads.pwe.dto;

import java.util.List;

import ads.pwe.model.Profissional;

public record DadosProfissionalRes(
    Integer idProfissional,
    String nomeProfissional,
    List<DadosEspecialidadeRes> especialidades
) {
    
    public DadosProfissionalRes(Profissional profissional) {
        this(
            profissional.getIdProfissional(),
            profissional.getNomeProfissional(),
            profissional.getEspecialidades()
                .stream()
                .map(especialidade -> new DadosEspecialidadeRes(especialidade))
                .toList()
        );
    }

}
