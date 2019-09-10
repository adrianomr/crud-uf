package uf.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErroEntity {
    String erro;
    String target;

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
