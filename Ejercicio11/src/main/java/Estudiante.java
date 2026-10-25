import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Estudiante {

    @XmlAttribute(name = "idMatricula")
    private int idMatricula;
    private String nombre;
    private Direccion direccion;
    private String telefono;
    private String email;
    @XmlElementWrapper(name = "matricula")//Con esto le comunicamos a JAXB que la lista matricula que contiene modulos envuelve a dichos modulos.
    @XmlElement(name = "modulo")//Indica como se llama cada elemento de la lista matricula que contiene modulos
    private List<Modulo> matricula;


    public Estudiante() {
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
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

    public List<Modulo> getMatricula() {
        return matricula;
    }

    public void setMatricula(List<Modulo> matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "idMatricula=" + idMatricula +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", matricula=" + matricula +
                '}';
    }
}
