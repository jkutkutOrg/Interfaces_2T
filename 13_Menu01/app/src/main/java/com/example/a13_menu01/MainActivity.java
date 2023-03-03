package com.example.a13_menu01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Main activity.
 *
 * @author Kiol12 and Jkutkut
 */
public class MainActivity extends AppCompatActivity {

    MenuItem modoNocturno;

    private final int[] OPCIONES = {
            R.id.op1,
            R.id.op2,
            R.id.op3,
            R.id.op4
    };

    private final int[] BASICOS = {
            R.id.edit_action,
            R.id.delete_action,
            R.id.share_action,
            R.id.create_pdf_action,
            R.id.print_action,
            R.id.send_action
    };

    /**
     * On create methos.
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.title));
    }

    /**
     * On create options menu. Inflates the menu with the main_menu.xml file and the style.
     * @param menu The menu created
     * @return True if the menu is created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        modoNocturno = menu.findItem(R.id.modo_nocturno);
        modoNocturno.setChecked(darkMode());
        return true;
    }

    /**
     * On options item selected. Shows a toast with the selected option.
     * @param item The selected item.
     * @return True if the item is selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.modo_nocturno){
            if (item.isChecked())
                alert("Activando modo nocturno!");
            else
                alert("Desactivando modo nocturno!");
            toggleDarkLightMode();
        }
        else if (any(id, BASICOS))
            alert(String.format("Ha pulsado %s", item.getTitle()));
        else if(any(id, OPCIONES))
            alert(String.format("%s seleccionada", item.getTitle()));
        return super.onOptionsItemSelected(item);
    }

    /**
     * Shows a toast message on the screen.
     * @param msg The message to show.
     */
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

    // TOOLS

    /**
     * Checks if the given integer is the the given array.
     * @param id The integer to check.
     * @param ids The array to check.
     * @return True if the integer is in the array.
     */
    private boolean any(int id, int[] ids){
        if (ids == null)
            return false;
        for (int j : ids) {
            if (id == j)
                return true;
        }
        return false;
    }
}