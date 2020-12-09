package volley.prueba.volley_manuel_valverde_arco_prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import volley.prueba.volley_manuel_valverde_arco_prueba.entity.Persona;
import volley.prueba.volley_manuel_valverde_arco_prueba.view.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private Button btInsert, btSelect, btUpdate, btDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        init();
    }

    private void init() {
        btInsert = findViewById(R.id.btInsertar);
        btSelect = findViewById(R.id.btSelect);
        btUpdate = findViewById(R.id.btUpdate);
        btDelete = findViewById(R.id.btDelete);
        initEvents();
    }

    private void initEvents() {
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.insertarPersona(MainViewModel.p);
            }
        });

        btSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.selectPersona(MainViewModel.p.getId());
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona p = MainViewModel.p;
                p.setNombre("Juan");
                viewModel.updatePersona(p.getId(), p);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.deletePersona(MainViewModel.p.getId());
            }
        });
    }
}