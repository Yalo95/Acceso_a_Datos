package org.example;

import java.util.Objects;

public class Persona { //Clase Básica POJO que solo guarda datos

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String country;

    public Persona() { //Esta vacio porque Gson lo necesita para construir objetos al leer el JSON
    }

    public Persona(String firstName, String lastName, String email, String gender, String country) { //Constructor para crear el objeto Persona.
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }
    //Getter&Setters
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


    @Override
    public String toString() { //Metodo toString sobreescrito. Une los cinco atributos con comas.
        //Primero confirmamos si el país contiene una coma. Si la tiene, lo pone entre comillas.
        // Si hay un país con coma dentro al leer el CSV de nuevo se partiría en dos columnas
        String pais = country;
        if (pais != null && pais.contains(",")) {
            pais = "\"" + pais + "\"";
        }//devuelve la persona como una línea del CSV, con los datos separados por comas.
        return firstName + "," + lastName + "," + email + "," + gender + "," + pais;
    }


    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
    // Dos personas son iguales si tienen el mismo email, calculando un número a partir del email.
    //Si dos objetos son iguales según equals, deben tener el mismo hashCode.Por eso se calcula con el mismo campo que usa equals.
    @Override
    public boolean equals(Object obj) { //Queremos que dos Persona sean iguales si tienen el mismo email, aunque sean objetos distintos.
        // Se elige el email porque es el único dato que identifica a cada persona
        if (obj instanceof Persona) {//Comprobamos que el objeto es realmente una Persona. Si fuera un String o un número, no tendría sentido comparar emails.
            // Además, si obj es null, instanceof da false, así que también cubre ese caso.
            Persona otra = (Persona) obj;//Esto es un cast, convertimos el Object en Persona. Es seguro porque con el if anterior comprobamos
            //que es tipo Persona. El casteo hace falta porque, aunque sabemos que es una Persona, Java solo ve un Object, y un Object no tiene el atributo
            //email. Con el cast ya podemos acceder a otra.email
            return email.equals(otra.email);
            //email es el email de persona que llama al método) y otra.email es el email de la otra persona.
            // Con String.equals comparamos el contenido de los dos textos, y se devuelve true si son iguales.
        }
        return false;
    }
}