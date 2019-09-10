package uf.servico;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.BeforeClass;
import org.junit.Test;
import uf.modelo.Uf;
import uf.util.ErroEntity;
import uf.util.Jetty.JettyServer;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UfServicoTest {

    @BeforeClass
    public static void startJetty() throws Exception {
        JettyServer server = new JettyServer();
        JettyServer.iniciaServidor(false);
    }

    @Test
    public void crudMethodsIntegrationTest() throws IOException {
        Uf novaUf = new Uf();
        novaUf.setDescricao("Teste");
        novaUf.setUf("Teste");
        String url = "http://localhost:5000/servico/uf";
        URLConnection connection = new URL(url).openConnection();
        final javax.ws.rs.client.Client client = ClientBuilder.newClient();
        ObjectMapper mapper = new ObjectMapper();

        //insere Ufs Teste
        final Response postResponse = client.target(url).request().post(Entity.json(novaUf));
        final Response responseGetInicial = client.target(url).request().get();
        String ufs = responseGetInicial.readEntity(String.class);
        ArrayList<Uf> ufsList = mapper.readValue(ufs, new TypeReference<ArrayList<Uf>>() {
        });
        int initialSize = ufsList.size() - 1;
        //altera Ufs Teste
        ufsList.forEach(uf -> {
            if (uf.getDescricao().equalsIgnoreCase("Teste") && uf.getUf().equalsIgnoreCase("Teste")) {
                uf.setUf("Eliminar");
                final Response deleteResponse = client.target(url + "/" + uf.getUuid()).request().put(Entity.json(uf));
            }
        });

        // elimina ufs de teste
        final Response responseGetEliminar = client.target(url).request().get();
        ufs = responseGetEliminar.readEntity(String.class);
        ufsList = mapper.readValue(ufs, new TypeReference<ArrayList<Uf>>() {
        });
        ufsList.forEach(uf -> {
            if (uf.getDescricao().equalsIgnoreCase("Teste") && uf.getUf().equalsIgnoreCase("Eliminar")) {
                final Response deleteResponse = client.target(url + "/" + uf.getUuid()).request().delete();
            }
        });
        final Response responseGetFinal = client.target(url).request().get();
        ufs = responseGetFinal.readEntity(String.class);
        ufsList = mapper.readValue(ufs, new TypeReference<ArrayList<Uf>>() {
        });
        assertEquals(initialSize, ufsList.size());
    }

    @Test
    public void postFaltandoDescricaoDeveRetornarErro() throws Exception {
        Uf novaUf = new Uf();
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://localhost:5000/servico/uf";
        URLConnection connection = new URL(url).openConnection();
        final javax.ws.rs.client.Client client = ClientBuilder.newClient();
        final Response postResponse = client.target(url).request().post(Entity.json(novaUf));
        String resp = postResponse.readEntity(String.class);
        ErroEntity erroEntity = mapper.readValue(resp, new TypeReference<ErroEntity>() {
        });
        assertEquals("descricao", erroEntity.getTarget());
    }



}