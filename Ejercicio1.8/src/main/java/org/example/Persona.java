package personal;

import java.util.Objects;

/** Clase POJO: una fila del fichero personal.csv */
public class Persona {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String country;

    public Persona() {
    }

    public Persona(String firstName, String lastName, String email, String gender, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    /** Igual que un registro del CSV: atributos separados por comas. */
    @Override
    public String toString() {
        // Si el pais lleva comas (ej. "Bonaire, Saint Eustatius and Saba") se pone entre comillas
        String pais = country;
        if (pais != null && pais.contains(",")) {
            pais = "\"" + pais + "\"";
        }
        return firstName + "," + lastName + "," + email + "," + gender + "," + pais;
    }

    // Dos personas son iguales si tienen el mismo email
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona otra = (Persona) obj;
        return Objects.equals(email, otra.email);
    }
}