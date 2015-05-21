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

    ArrayList<Animal> animals;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animalindex);
        animals = new ArrayList<Animal>();

        // animals
        Animal mudkip = new Animal(R.drawable.icon, "Mudkip");
        Animal ko = new Animal(R.drawable.icon, "Ko");
        Animal tiger = new Animal(R.drawable.icon, "Tiger");
        Animal pingvin = new Animal(R.drawable.icon, "Pingvin");
        Animal emil = new Animal(R.drawable.icon, "Emil");
        Animal spatte1 = new Animal(R.drawable.icon, "Spaette");
        Animal ko1 = new Animal(R.drawable.icon, "Ko");
        Animal tiger1 = new Animal(R.drawable.icon, "Tiger");
        Animal pingvin1 = new Animal(R.drawable.icon, "Pingvin");
        Animal emil1 = new Animal(R.drawable.icon, "Emil");

        animals.add(mudkip);
        animals.add(ko);
        animals.add(tiger);
        animals.add(pingvin);
        animals.add(emil);
        animals.add(spatte1);
        animals.add(ko1);
        animals.add(tiger1);
        animals.add(pingvin1);
        animals.add(emil1);

        listView = (ListView) findViewById(R.id.animal_list);
        AnimalIndexAdapter adapter = new AnimalIndexAdapter(this, animals);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Animal selectedAnimal = animals.get(position);
        Intent videoStart = new Intent(this,VideoActivity.class);
        videoStart.putExtra("VIDEONAME", selectedAnimal.getName().toLowerCase());
        startActivity(videoStart);
        finish();
    }
}
