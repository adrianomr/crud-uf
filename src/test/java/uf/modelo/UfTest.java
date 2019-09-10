package uf.modelo;

import org.junit.Test;
import uf.excecoes.CampoEmBrancoExcecao;
import uf.util.Acao;

public class UfTest {

    @Test(expected = CampoEmBrancoExcecao.class)
    public void validaTesteDeveRetonaraExcecao() throws Exception {
        Uf uf = new Uf();
        uf.valida(Acao.tipo.INCLUI);
    }

}