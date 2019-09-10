package uf.excecoes;

import uf.util.ErroEntity;

public class CampoEmBrancoExcecao extends Excecoes {
    String campo;

    public CampoEmBrancoExcecao() {
        super("O campo não pode ficar em branco.");
    }

    @Override
    public ErroEntity getErro() {
        ErroEntity erro = new ErroEntity();
        erro.setErro(this.getMessage());
        erro.setTarget(this.campo);
        return erro;
    }

    public String getCampo() {
        return campo;
    }

    public CampoEmBrancoExcecao setCampo(String campo) {
        this.campo = campo;
        return this;
    }
}
