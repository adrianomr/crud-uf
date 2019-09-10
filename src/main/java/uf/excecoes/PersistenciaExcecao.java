package uf.excecoes;

import uf.util.ErroEntity;

public class PersistenciaExcecao extends Excecoes {

    public PersistenciaExcecao() {
        super("Não foi possível concluir a solicitação.");
    }

    @Override
    public ErroEntity getErro() {
        ErroEntity erro = new ErroEntity();
        erro.setErro(this.getMessage());
        return erro;
    }
}
