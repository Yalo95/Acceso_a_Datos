import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "estudiantado")
@XmlAccessorType(XmlAccessType.FIELD)
public class Estudiantado {
    @XmlElement(name = "estudiante")
    @SerializedName("estudiantado")//En el archivo Json la etiqueta valor es estudiantado y no estudiante, si no se lo remarcamos no la podria leer.
    private List<Estudiante> estudiante;

    public Estudiantado() {
    }

    public List<Estudiante> getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(List<Estudiante> estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "Estudiantado{" +
                "estudiante=" + estudiante +
                '}';
    }
}
