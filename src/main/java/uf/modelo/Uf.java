package uf.modelo;

import org.hibernate.annotations.GenericGenerator;
import uf.excecoes.CampoEmBrancoExcecao;
import uf.util.Acao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@XmlRootElement
public class Uf implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;
    private String uf;
    private String descricao;
    private Date data;

    public Uf() {
        this.data = new Date();
    }

    public boolean valida(Acao.tipo acao) throws Exception {
        switch (acao) {
            case INCLUI:
            case ALTERA:
                if (getDescricao().isEmpty())
                    throw new CampoEmBrancoExcecao().setCampo("descricao");
                if (getUf().isEmpty())
                    throw new CampoEmBrancoExcecao().setCampo("uf");
                break;
            case DELETA:
                break;
        }
        return true;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUf() {
        return uf == null ? "" : uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDescricao() {
        return descricao == null ? "" : descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uf uf = (Uf) o;
        return uuid.equals(uf.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
