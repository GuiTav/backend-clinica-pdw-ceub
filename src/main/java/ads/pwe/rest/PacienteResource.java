package ads.pwe.rest;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import ads.pwe.dto.CpfPacienteReq;
import ads.pwe.dto.DadosPacienteReq;
import ads.pwe.model.Paciente;
import ads.pwe.repository.PacienteRepository;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("paciente")
@Tag(name = "Paciente")
public class PacienteResource {
    
    @Inject
    PacienteRepository pacienteRepository;

    @GET
    public List<Paciente> listarPacientes() {
        return pacienteRepository.listAll();
    }

    @GET
    @Path("/encontrar")
    public Paciente encontrarPaciente(@Valid CpfPacienteReq dados) {
        return pacienteRepository.encontrarPacientePorCpf(dados.cpf());
    }

    @POST
    public Paciente criarPaciente(@Valid DadosPacienteReq dados) {
        return pacienteRepository.salvarPaciente(dados);
    }

    @PUT
    public Paciente editarPaciente(
        @Valid DadosPacienteReq dados
    ) {
        return pacienteRepository.editarPaciente(dados);
    }

    @DELETE
    public void deletarPaciente(@Valid CpfPacienteReq dados) {
        var paciente = pacienteRepository.encontrarPacientePorCpf(dados.cpf());
        pacienteRepository.delete(paciente);
    }

    @ServerExceptionMapper
    public RestResponse<String> mapException(NotFoundException exception) {
        return RestResponse.status(Response.Status.NOT_FOUND, exception.getMessage());
    }

}
