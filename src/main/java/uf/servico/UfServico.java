package uf.servico;

import uf.dao.UfDAO;
import uf.modelo.Uf;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("servico")
public class UfServico {
    private UfDAO ufDAO;

    public UfServico() {
        this.ufDAO = UfDAO.getInstance();
    }

    @GET
    @Path("uf")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Uf> getUfList() {
        return ufDAO.findAll();
    }

    @POST
    @Path("uf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uf incluiUf(Uf uf) {
        ufDAO.persist(uf);
        return uf;
    }

    @PUT
    @Path("uf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uf alteraUf(Uf uf) {
        ufDAO.merge(uf);
        return uf;
    }

    @DELETE
    @Path("uf")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uf deletaUf(Uf uf) {
        ufDAO.remove(uf);
        return uf;
    }
}
