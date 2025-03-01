package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.DadosProfissionalReq;
import ads.pwe.model.Profissional;
import ads.pwe.repository.ProfissionalRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("profissional")
@Tag(name = "Profissional")
public class ProfissionalResource {

    @Inject
    ProfissionalRepository profissionalRepository;
    
    @GET
    public List<Profissional> listarProfissionais() {
        return profissionalRepository.listAll();
    }

    @GET
    @Path("/{idProfissional}")
    public Profissional encontrarProfissional(@RestPath Integer idProfissional) {
        return profissionalRepository.encontrarProfissionalPorId(idProfissional);
    }

    @POST
    public Profissional criarProfissional(DadosProfissionalReq dados) {
        return profissionalRepository.salvarProfissional(dados);
    }

    @PUT
    @Path("/{idProfissional}")
    public Profissional editarProfissional(
        @RestPath Integer idProfissional,
        DadosProfissionalReq dados) {
        return profissionalRepository.editarProfissional(idProfissional, dados);
    }

    @DELETE
    @Path("/{idProfissional}")
    public void deletarProfissional(@RestPath Integer idProfissional) {
        var profissional = profissionalRepository.encontrarProfissionalPorId(idProfissional);
        profissionalRepository.delete(profissional);
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException exception) {
        return RestResponse.status(Response.Status.NOT_FOUND, exception.getMessage());
    }

}
