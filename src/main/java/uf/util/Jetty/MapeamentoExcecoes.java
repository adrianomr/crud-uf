package uf.util.Jetty;

import uf.excecoes.Excecoes;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MapeamentoExcecoes implements
        ExceptionMapper<Excecoes> {

    @Override
    public Response toResponse(Excecoes exception) {
        return Response.status(406).entity(exception.getErro())
                .type(MediaType.APPLICATION_JSON).build();
    }
}