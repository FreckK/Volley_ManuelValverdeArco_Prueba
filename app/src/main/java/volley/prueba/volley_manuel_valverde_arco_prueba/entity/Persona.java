package volley.prueba.volley_manuel_valverde_arco_prueba.entity;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Persona {

    private int id, edad;
    private String nombre, DNI;
    private float altura;

    public Persona() {
        this(-1, -1, "", "", -1);
    }

    public Persona(int id, int edad, String nombre, String DNI, float altura) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.DNI = DNI;
        this.altura = altura;
    }

    public Persona(int edad, String nombre, String DNI, float altura) {
        this.edad = edad;
        this.nombre = nombre;
        this.DNI = DNI;
        this.altura = altura;
    }

    public int getId() {
        return id;
    }

    public Persona setId(int id) {
        this.id = id;
        return this;
    }

    public int getEdad() {
        return edad;
    }

    public Persona setEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Persona setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getDNI() {
        return DNI;
    }

    public Persona setDNI(String DNI) {
        this.DNI = DNI;
        return this;
    }

    public float getAltura() {
        return altura;
    }

    public Persona setAltura(float altura) {
        this.altura = altura;
        return this;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", edad=" + edad +
                ", nombre='" + nombre + '\'' +
                ", DNI='" + DNI + '\'' +
                ", altura=" + altura +
                '}';
    }

    public static Persona jsonToPersona(JSONObject json) throws JSONException {
        Persona p = new Persona();
        p.setId(json.getInt("id"));
        p.setNombre(json.getString("nombre"));
        p.setDNI(json.getString("DNI"));
        p.setAltura((float) json.getDouble("altura"));
        p.setEdad(json.getInt("edad"));
        return p;
    }

    public static HashMap<String, String> personaToHashmap(Persona persona){
        HashMap<String, String> json = new HashMap<>();
        json.put("id", String.valueOf(persona.id));
        json.put("nombre", persona.nombre);
        json.put("DNI", persona.DNI);
        json.put("altura", String.valueOf(persona.altura));
        json.put("edad", String.valueOf(persona.edad));
        return json;
    }
}
