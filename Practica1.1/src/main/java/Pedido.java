import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido {

    @XmlAttribute(name = "id")
    private String id;
    @XmlElement(name = "fecha")
    private String fecha;
    @XmlElement(name = "total")
    private double total;
    @XmlElementWrapper(name = "articulos")
    @XmlElement(name = "articulo")
    private List<Articulo> articulos = new ArrayList<>();


    public Pedido() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Articulo> articulos) {
        this.articulos = articulos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", articulos=" + articulos +
                '}';
    }
}
