package uf.servico;

import uf.dao.UfDAO;
import uf.modelo.Uf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("servico")
public class UfServico {
    @GET
    @Path("uf")
    @Produces({"application/json"})
    public List<Uf> getUfList() {
        UfDAO ufDAO = UfDAO.getInstance();
        return ufDAO.findAll();
    }
}
