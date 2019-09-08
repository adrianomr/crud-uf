package uf.servico;

import uf.dao.UfDAO;
import uf.modelo.Uf;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
    public Uf incluiUf(Uf uf) {
        ufDAO.persist(uf);
        return uf;
    }

    @PUT
    @Path("/{chave}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uf alteraUf(Uf uf, @PathParam("chave") String chave) {
        uf.setUuid(chave);
        ufDAO.merge(uf);
        return uf;
    }

    @DELETE
    @Path("/{chave}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uf deletaUf(@PathParam("chave") String chave) {
        return ufDAO.removeById(chave);
    }
}
