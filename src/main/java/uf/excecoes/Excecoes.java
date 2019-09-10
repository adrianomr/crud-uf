package uf.excecoes;

import uf.util.ErroEntity;

public class Excecoes extends Exception {
    public Excecoes(String mensagem) {
        super(mensagem);
    }

    public ErroEntity getErro() {
        ErroEntity erro = new ErroEntity();
        erro.setErro(this.getMessage());
        return erro;
    }
}
