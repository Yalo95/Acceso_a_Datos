import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente {

    @XmlAttribute(name = "dni")
    private String dni;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlElement(name = "direccion")
    private String direccion;
    @XmlElement(name = "telefono")
    private String telefono;
    @XmlElement(name = "email")
    private String email;
    @XmlElementWrapper(name = "pedidos")
    @XmlElement(name = "pedido")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", pedidos=" + pedidos +
                '}' + "\n";
    }
    public void añadirPedido(Pedido pedido) { //Con este metodo nos aseguramos de que si alguna vez hay un cambio en la estructura de datos de Clientes,
        // solamente debamos cambiar este metodo y no todos los getPedidos de otras partes del codigo, porque ya no devolveria una lista.
        pedidos.add(pedido);

    }
}
