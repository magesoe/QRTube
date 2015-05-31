package dk.magesoe.qrtube;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by FMM on 21-05-2015.
 */
public class AnimalIndex extends Activity implements AdapterView.OnItemClickListener {

    AnimalDatabase animalDatabase;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalindex);
        animalDatabase = new AnimalDatabase(getApplicationContext());


        listView = (ListView) findViewById(R.id.animal_list);
        AnimalIndexAdapter adapter = new AnimalIndexAdapter(this, animalDatabase.getAnimalList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Animal selectedAnimal = animalDatabase.getAnimalList().get(position);
        Intent videoStart = new Intent(this,VideoActivity.class);
        videoStart.putExtra("VIDEONAME", selectedAnimal.getVideoname());
        videoStart.putExtra("NAME", selectedAnimal.getName());
        videoStart.putExtra("DESC", selectedAnimal.getDesc());
        videoStart.putExtra("HABITAT", selectedAnimal.getHabitat());
        videoStart.putExtra("WEIGHT", selectedAnimal.getWeight());
        videoStart.putExtra("EATS", selectedAnimal.getEats());
        videoStart.putExtra("CAMEFROM", "index");
        startActivity(videoStart);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }
}
