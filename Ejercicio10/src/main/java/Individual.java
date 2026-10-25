import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class Individual {

    @XmlElement(name = "name")
    private String name; //Private para que no pueda ser cambiado desde otra clase como el main.
    @XmlElement(name = "address")
    private Address address;// Se pone asi porque en el xsd no hya maxOccurs ni MinOccurs, solo puede haber una direccion con una sola coiudad, zip y calle
    //Llama a address porque tiene que estar enlazada con la clase Address

    public Individual() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() { //Para seguir imprimiendo por pantalla la informacion
        return "{Nombre: '" + name + "'" + " Dirección: " + address + "}";
    }
}
