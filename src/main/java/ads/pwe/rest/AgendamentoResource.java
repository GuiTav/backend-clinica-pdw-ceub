package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.DadosAgendamentoReq;
import ads.pwe.model.Agendamento;
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
    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.listAll();
    }

    @GET
    @Path("/{idAgendamento}")
    public Agendamento encontrarAgendamento(@RestPath Integer idAgendamento) {
        return agendamentoRepository.encontrarAgendamentoPorId(idAgendamento);
    }

    @POST
    public Agendamento criarAgendamento(DadosAgendamentoReq dados) {
        return agendamentoRepository.salvarAgendamento(dados);
    }

    @PUT
    @Path("/{idAgendamento}")
    public Agendamento editarAgendamento(
        @RestPath Integer idAgendamento,
        DadosAgendamentoReq dados) {
        return agendamentoRepository.editarAgendamento(idAgendamento, dados);
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
