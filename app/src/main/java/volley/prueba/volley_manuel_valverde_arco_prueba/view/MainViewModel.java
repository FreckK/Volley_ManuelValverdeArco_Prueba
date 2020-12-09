package volley.prueba.volley_manuel_valverde_arco_prueba.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import volley.prueba.volley_manuel_valverde_arco_prueba.entity.Persona;
import volley.prueba.volley_manuel_valverde_arco_prueba.model.Repository;

public class MainViewModel extends AndroidViewModel {

    private Repository repository;
    public static final Persona p = new Persona(12, "Alvaro", "12345678P", 1.78f);

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application.getApplicationContext());
    }

    public boolean insertarPersona(Persona persona){
        return repository.insertarPersona(persona);
    }

    public Persona selectPersona(int id){
        return repository.selectPersona(id);
    }

    public boolean updatePersona(int id, Persona persona){
        return repository.updatePersona(id, persona);
    }

    public boolean deletePersona(int id){
        return repository.deletePersona(id);
    }
}

