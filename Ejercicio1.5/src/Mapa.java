import java.io.Serializable;
import java.util.HashMap;

public class Mapa implements Serializable {

    HashMap<String,Localidad> localidades = new HashMap<>();

    public HashMap<String, Localidad> getLocalidades() { // Devuelve el HashMap de localidades
        return localidades;
    }

    public void setLocalidades(HashMap<String, Localidad> localidades) { // Establece el HashMap de localidades
        this.localidades = localidades;
    }

    public void addLocalidad(Localidad localidad) { // Agrega una localidad al mapa usando su ID como clave en el HashMap
        localidades.put(localidad.getId(), localidad);
    }

    @Override
    public String toString() { // Devuelve un String con la información de todas las localidades en el mapa
        String texto = "";
        for (Localidad l : localidades.values()) {
            texto += " " + l + "\n";
        }
        return texto;
    }
    public float [] getCoordenadas(String name) { // Devuelve un array con las coordenadas de la localidad con el nombre especificado
        for (Localidad l : localidades.values()) { //El for pasa por cada localidad del mapa, los valores del mapa.
            if(l.getName().equalsIgnoreCase(name.trim())){ //Cogemos el nombre de la localidad y le quitamos los espacios con trim.
                                                            // El equals comprara los dos nombres (Strings) y con el IgnoreCase se ignora Mayus y Minusculas.
                return new float[] {l.getLatitude(), l.getLongitude()}; //Guardamos en un array la longitud y la latitud
            }
        }
        return null; //Devuelve null si no hay coincidencia.
    }



}
