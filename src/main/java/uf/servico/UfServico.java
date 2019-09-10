package uf.servico;

import uf.dao.UfDAO;
import uf.excecoes.PersistenciaExcecao;
import uf.modelo.Uf;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("servico/uf")
public class UfServico {
    private UfDAO ufDAO;

    public UfServico() {
        this.ufDAO = UfDAO.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Uf> getUfList() {
        return ufDAO.findAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluiUf(Uf uf) throws Exception {
        ufDAO.persist(uf);
        return Response
                .status(Response.Status.OK)
                .entity(uf)
                .build();
    }

    @PUT
    @Path("/{chave}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alteraUf(Uf uf, @PathParam("chave") String chave) throws Exception {
        uf.setUuid(chave);
        ufDAO.merge(uf);
        return Response
                .status(Response.Status.OK)
                .entity(uf)
                .build();
    }

    @DELETE
    @Path("/{chave}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uf deletaUf(@PathParam("chave") String chave) throws PersistenciaExcecao {
        return ufDAO.removeById(chave);
    }
}
