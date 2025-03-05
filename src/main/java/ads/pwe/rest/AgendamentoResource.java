package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.DadosAgendamentoReq;
import ads.pwe.dto.DadosAgendamentoRes;
import ads.pwe.repository.AgendamentoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("agendamento")
@Tag(name = "Agendamento")
public class AgendamentoResource {
    
    @Inject
    AgendamentoRepository agendamentoRepository;
    
    @GET
    public List<DadosAgendamentoRes> listarAgendamentos() {
        return agendamentoRepository.listAll()
            .stream()
            .map(agendamento -> new DadosAgendamentoRes(agendamento))
            .toList();
    }

    @GET
    @Path("/{idAgendamento}")
    public DadosAgendamentoRes encontrarAgendamento(@RestPath Integer idAgendamento) {
        return new DadosAgendamentoRes(
            agendamentoRepository.encontrarAgendamentoPorId(idAgendamento)
        );
    }

    @POST
    public DadosAgendamentoRes criarAgendamento(DadosAgendamentoReq dados) {
        return new DadosAgendamentoRes(
            agendamentoRepository.salvarAgendamento(dados)
        );
    }

    @PUT
    @Path("/{idAgendamento}")
    public DadosAgendamentoRes editarAgendamento(
        @RestPath Integer idAgendamento,
        DadosAgendamentoReq dados) {
        return new DadosAgendamentoRes(
            agendamentoRepository.editarAgendamento(idAgendamento, dados)
        );
    }

    @DELETE
    @Path("/{idAgendamento}")
    public void deletarAgendamento(@RestPath Integer idAgendamento) {
        var agendamento = agendamentoRepository.encontrarAgendamentoPorId(idAgendamento);
        agendamentoRepository.delete(agendamento);
    }

    @ServerExceptionMapper
    public RestResponse<String> mapExceptionNotFound(NotFoundException exception) {
        return RestResponse.status(Response.Status.NOT_FOUND, exception.getMessage());
    }

    @ServerExceptionMapper
    public RestResponse<String> mapExceptionBadRequest(BadRequestException exception) {
        return RestResponse.status(Response.Status.BAD_REQUEST, exception.getMessage());
    }

}
