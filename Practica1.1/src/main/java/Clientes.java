import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "clientes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Clientes {

    @XmlElement(name = "cliente")
    private List<Cliente> clientes = new ArrayList<>();

    public Clientes() {
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (Cliente c: this.clientes){
            sb.append(c.toString() + "\n"); //Aqui añadimos al builder en forma de string cada cliente y al final del recorrido le añadimos un salto de linea para separarlos.
        }
        return "Clientes[ \n" + sb.toString() + "]";
    }


}
