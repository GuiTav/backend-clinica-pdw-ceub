package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.DadosOperacaoReq;
import ads.pwe.dto.DadosOperacaoRes;
import ads.pwe.repository.OperacaoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("operacao")
@Tag(name = "Operacao")
public class OperacaoResource {
    
    @Inject
    OperacaoRepository operacaoRepository;

    @GET
    public List<DadosOperacaoRes> listarOperacoes() {
        return operacaoRepository.listAll()
            .stream()
            .map(operacao -> new DadosOperacaoRes(operacao))
            .toList();
    }

    @GET
    @Path("/{idOperacao}")
    public DadosOperacaoRes encontrarOperacao(@RestPath Integer idOperacao) {
        return new DadosOperacaoRes(
            operacaoRepository.encontrarOperacaoPorId(idOperacao)
        );
    }

    @POST
    public DadosOperacaoRes criarOperacao(DadosOperacaoReq dados) {
        return new DadosOperacaoRes(
            operacaoRepository.salvarOperacao(dados)
        );
    }

    @PUT
    @Path("/{idOperacao}")
    public DadosOperacaoRes editarOperacao(
        @RestPath Integer idOperacao,
        DadosOperacaoReq dados) {
        return new DadosOperacaoRes(
            operacaoRepository.editarOperacao(idOperacao, dados)
        );
    }

    @DELETE
    @Path("/{idOperacao}")
    public void deletarOperacao(@RestPath Integer idOperacao) {
        var operacao = operacaoRepository.encontrarOperacaoPorId(idOperacao);
        operacaoRepository.delete(operacao);
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException exception) {
        return RestResponse.status(Response.Status.NOT_FOUND, exception.getMessage());
    }

}
