package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.DadosProfissionalReq;
import ads.pwe.dto.DadosProfissionalRes;
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
    public List<DadosProfissionalRes> listarProfissionais() {
        return profissionalRepository.listAll()
            .stream()
            .map(pro -> new DadosProfissionalRes(pro))
            .toList();
    }

    @GET
    @Path("/{idProfissional}")
    public DadosProfissionalRes encontrarProfissional(@RestPath Integer idProfissional) {
        return new DadosProfissionalRes(
            profissionalRepository.encontrarProfissionalPorId(idProfissional)
        );
    }

    @POST
    public DadosProfissionalRes criarProfissional(DadosProfissionalReq dados) {
        return new DadosProfissionalRes(
            profissionalRepository.salvarProfissional(dados)
        );
    }

    @PUT
    @Path("/{idProfissional}")
    public DadosProfissionalRes editarProfissional(
        @RestPath Integer idProfissional,
        DadosProfissionalReq dados) {
        return new DadosProfissionalRes(
            profissionalRepository.editarProfissional(idProfissional, dados)
        );
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
