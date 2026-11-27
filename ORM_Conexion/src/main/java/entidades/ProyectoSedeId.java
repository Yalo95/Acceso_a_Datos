package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProyectoSedeId implements Serializable {
    @Serial
    private static final long serialVersionUID = -2311629933387497406L;
    @Column(name = "id_proy", nullable = false)
    private Integer idProy;

    @Column(name = "id_sede", nullable = false)
    private Integer idSede;

    public Integer getIdProy() {
        return idProy;
    }

    public void setIdProy(Integer idProy) {
        this.idProy = idProy;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProyectoSedeId entity = (ProyectoSedeId) o;
        return Objects.equals(this.idProy, entity.idProy) &&
                Objects.equals(this.idSede, entity.idSede);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProy, idSede);
    }
}