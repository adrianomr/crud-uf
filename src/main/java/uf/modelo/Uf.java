package uf.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Uf {
    @Id
    @GeneratedValue
    private String uuid;

    private String uf;
    private String descricao;
    private Date data;
}
