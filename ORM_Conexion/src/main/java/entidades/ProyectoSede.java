package entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "proyecto_sede")
public class ProyectoSede {
    @EmbeddedId
    private ProyectoSedeId id;

    @MapsId("idProy")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proy", nullable = false)
    private Proyecto idProy;

    @MapsId("idSede")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sede", nullable = false)
    private Sede idSede;

    @Column(name = "f_inicio", nullable = false)
    private LocalDate fInicio;

    @Column(name = "f_fin")
    private LocalDate fFin;

    public ProyectoSedeId getId() {
        return id;
    }

    public void setId(ProyectoSedeId id) {
        this.id = id;
    }

    public Proyecto getIdProy() {
        return idProy;
    }

    public void setIdProy(Proyecto idProy) {
        this.idProy = idProy;
    }

    public Sede getIdSede() {
        return idSede;
    }

    public void setIdSede(Sede idSede) {
        this.idSede = idSede;
    }

    public LocalDate getFInicio() {
        return fInicio;
    }

    public void setFInicio(LocalDate fInicio) {
        this.fInicio = fInicio;
    }

    public LocalDate getFFin() {
        return fFin;
    }

    public void setFFin(LocalDate fFin) {
        this.fFin = fFin;
    }

}