package com.example.a13_menu01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MenuItem modoNocturno;

    /*
    En este ejemplo vamos a ver como podemos trabajar con un menu de
    opciones en android

    En primer lugar, debes ubicar crear un archivo de menú y ubicarlo en la carpeta
    res/menu/nombre_archivo.xml.

    Desde Android Studio se resume a dar click derecho en tu carpeta res y luego seleccionar
    New > Android Resource File.

    Al desplegarse la ventana de configuración, selecciona "Menu" en el tipo de recursos y
    luego ponemos su nombre "main_menu"

    Podemos crear los dibujos en New > Vector Asset en la carpeta de drawable
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Este metodo se llamara cuando se cree la actividad e inflaremos el menu
    //El parametro menu sera el objeto donde inflaremos el layout, que nos lo
    //crea android

    //En el valor de retorno decimos si queremos mostrar el menu o no
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        modoNocturno = menu.findItem(R.id.modo_nocturno);
        modoNocturno.setChecked(darkMode());
        return true;
    }

    //Con este método detectaremos que opción del menú ha sido pulsada
    //el parametro MenuItem representa el objeto que fue seleccionado, no puede ser null
    //En el valor de retorno decimos si queremos procesar el elemento en este metodo o no
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.edit_action) {
            alert("Ha pulsado " + item.getTitle().toString());
        }else if(id == R.id.modo_nocturno){
            if (item.isChecked())
                alert("Activando modo nocturno!");
            else
                alert("Desactivando modo nocturno!");
            toggleDarkLightMode();
            return true;
        }else if(id == R.id.op1){
            Toast.makeText(this,"Opcion 1 elegida!",Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void alert(String msg) {
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Toggle between dark and light mode.
     */
    protected void toggleDarkLightMode() {
        if (darkMode())
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    /**
     * Check if the current mode is dark.
     * @return True if the current mode is dark.
     */
    protected boolean darkMode() {
        int nightModeFlags = this.getResources().getConfiguration().uiMode &
                Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }
}