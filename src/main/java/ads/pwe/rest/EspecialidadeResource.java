package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.DadosEspecialidadeReq;
import ads.pwe.model.Especialidade;
import ads.pwe.repository.EspecialidadeRepository;
import jakarta.inject.Inject;
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
    public List<Especialidade> listarEspecialidades() {
        return especialidadeRepository.listAll();
    }

    @GET
    @Path("/{idEspecialidade}")
    public Especialidade encontrarEspecialidade(@RestPath Integer idEspecialidade) {
        return especialidadeRepository.encontrarEspecialidadePorId(idEspecialidade);
    }

    @POST
    public Especialidade criarEspecialidade(DadosEspecialidadeReq dados) {
        return especialidadeRepository.salvarEspecialidade(dados);
    }

    @PUT
    @Path("/{idEspecialidade}")
    public Especialidade editarEspecialidade(
        @RestPath Integer idEspecialidade,
        DadosEspecialidadeReq dados) {
        return especialidadeRepository.editarEspecialidade(idEspecialidade, dados);
    }

    @DELETE
    @Path("/{idEspecialidade}")
    public void deletarEspecialidade(@RestPath Integer idEspecialidade) {
        var especialidade = especialidadeRepository.encontrarEspecialidadePorId(idEspecialidade);
        especialidadeRepository.delete(especialidade);
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException exception) {
        return RestResponse.status(Response.Status.NOT_FOUND, exception.getMessage());
    }

}
