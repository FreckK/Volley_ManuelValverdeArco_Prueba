package volley.prueba.volley_manuel_valverde_arco_prueba.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import volley.prueba.volley_manuel_valverde_arco_prueba.entity.Persona;

public class Repository {

    private Context context;
    String url = "https://192.168.0.254/managePersona.php";

    public Repository(Context context){
        this.context = context;
    }

    public boolean insertarPersona(Persona persona){
        final boolean[] estaInsertado = {false};
        RequestQueue queue = Volley.newRequestQueue(context);
        HashMap<String, String> data = Persona.personaToHashmap(persona);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    estaInsertado[0] = response.getBoolean("estaInsertado");
                    Log.d("response", "Resultado de la insercion: " + response.getBoolean("estaInsertado"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("resp", error.getLocalizedMessage());
            }
        });
        return estaInsertado[0];
    }

    public Persona selectPersona(int id){
        final Persona[] p = {new Persona()};
        RequestQueue queue = Volley.newRequestQueue(context);
        HashMap<String, String> data = new HashMap<>();
        data.put("id", String.valueOf(id));
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    p[0] = Persona.jsonToPersona(response);
                    Log.d("response", "Select: " + Persona.jsonToPersona(response).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("resp", error.getLocalizedMessage());
            }
        });
        return p[0];
    }

    public boolean updatePersona(int id, Persona persona){
        final boolean[] estaActualizado = {false};
        RequestQueue queue = Volley.newRequestQueue(context);
        HashMap<String, String> data = Persona.personaToHashmap(persona);
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    estaActualizado[0] = response.getBoolean("estaActualizado");
                    Log.d("response", "Resultado del update: " + response.getBoolean("estaActualizado"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("resp", error.getLocalizedMessage());
            }
        });
        return estaActualizado[0];
    }

    public boolean deletePersona(int id){
        final boolean[] estaEliminado = {false};
        RequestQueue queue = Volley.newRequestQueue(context);
        HashMap<String, String> data = new HashMap<>();
        data.put("id", String.valueOf(id));
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.DELETE, url, new JSONObject(data), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    estaEliminado[0] = response.getBoolean("estaActualizado");
                    Log.d("response", "Resultado del delete: " + response.getBoolean("estaEliminado"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("resp", error.getLocalizedMessage());
            }
        });
        return estaEliminado[0];
    }
}
