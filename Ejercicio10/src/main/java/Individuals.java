import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "individuals")//Unicamente para el elemento raiz
@XmlAccessorType(XmlAccessType.FIELD)//Para que unicamente al hacer unmarshall se cojan los campos y no los getters and setters.
public class Individuals {

    @XmlElement(name = "individual")
    private List<Individual> individual;//Es lista porque es maxOccurs Unbounded, puede haber muchos individual. Lista si se repite

    public Individuals() {}

    public List<Individual> getIndividual() {
        return individual;
    }

    public void setIndividual(List<Individual> individual) {
        this.individual = individual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Individual individuos: this.individual){
            sb.append(individuos.toString() + "\n");
        }
        return "Individuos:[" + "\n" + sb.toString() +"]";
    }
}
