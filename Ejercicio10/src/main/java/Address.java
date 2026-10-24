import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @XmlElement(name = "zip")
    private int zip;
    @XmlElement(name = "city")
    private String city;
    @XmlElement(name = "street")
    private String street;

    public Address() {
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "{Código Postal: '" + zip + "'" + " Ciudad: '" + city +"'" + " Calle: '" + street + "'}";
    }
}
