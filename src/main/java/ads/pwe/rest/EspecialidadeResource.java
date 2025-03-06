package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.DadosEspecialidadeReq;
import ads.pwe.dto.DadosEspecialidadeRes;
import ads.pwe.repository.EspecialidadeRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("especialidade")
@Tag(name = "Especialidade")
public class EspecialidadeResource {
    
    @Inject
    EspecialidadeRepository especialidadeRepository;

    @GET
    @Transactional
    public List<DadosEspecialidadeRes> listarEspecialidades() {
        return especialidadeRepository.streamAll()
            .map(especialidade -> new DadosEspecialidadeRes(especialidade))
            .toList();
    }

    @GET
    @Path("/{idEspecialidade}")
    public DadosEspecialidadeRes encontrarEspecialidade(@RestPath Integer idEspecialidade) {
        return new DadosEspecialidadeRes(
            especialidadeRepository.encontrarEspecialidadePorId(idEspecialidade)
        );
    }

    @POST
    @Transactional
    public DadosEspecialidadeRes criarEspecialidade(DadosEspecialidadeReq dados) {
        return new DadosEspecialidadeRes(
            especialidadeRepository.salvarEspecialidade(dados)
        );
    }

    @PUT
    @Path("/{idEspecialidade}")
    @Transactional
    public DadosEspecialidadeRes editarEspecialidade(
        @RestPath Integer idEspecialidade,
        DadosEspecialidadeReq dados) {
        return new DadosEspecialidadeRes(
            especialidadeRepository.editarEspecialidade(idEspecialidade, dados)
        );
    }

    @DELETE
    @Path("/{idEspecialidade}")
    @Transactional
    public void deletarEspecialidade(@RestPath Integer idEspecialidade) {
        var especialidade = especialidadeRepository.encontrarEspecialidadePorId(idEspecialidade);
        especialidadeRepository.delete(especialidade);
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException exception) {
        return RestResponse.status(Response.Status.NOT_FOUND, exception.getMessage());
    }

}
