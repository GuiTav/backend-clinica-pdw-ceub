package ads.pwe.dto;

import java.util.List;

public record DadosProfissionalReq(
    String nomeProfissional,
    List<Integer> idsEspecialidades
) {
    
}
