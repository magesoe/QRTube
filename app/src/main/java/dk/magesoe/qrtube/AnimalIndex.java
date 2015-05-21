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
        Animal mudkip = new Animal(R.drawable.icon, "Mudkip","Mudkip is a small amphibious quadruped Pokemon. It has a blue body with a light-blue underside.","40 cm","Water","7,6 kg", "Pokemon food");
        Animal ko = new Animal(R.drawable.icon, "Ko", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal tiger = new Animal(R.drawable.icon, "Tiger", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal pingvin = new Animal(R.drawable.icon, "Pingvin", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal emil = new Animal(R.drawable.icon, "Emil", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal spatte1 = new Animal(R.drawable.icon, "Spaette", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal ko1 = new Animal(R.drawable.icon, "Ko", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal tiger1 = new Animal(R.drawable.icon, "Tiger", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal pingvin1 = new Animal(R.drawable.icon, "Pingvin", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");
        Animal emil1 = new Animal(R.drawable.icon, "Emil", "Ko desc", "Ko er stor", "Ko bor", "Ko vejer", "Ko spiser");

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
        videoStart.putExtra("NAME", selectedAnimal.getName());
        videoStart.putExtra("DESC", selectedAnimal.getDesc());
        videoStart.putExtra("HEIGHT", selectedAnimal.getHeight());
        videoStart.putExtra("HABITAT", selectedAnimal.getHabitat());
        videoStart.putExtra("WEIGHT", selectedAnimal.getWeight());
        videoStart.putExtra("EATS", selectedAnimal.getEats());
        startActivity(videoStart);
        finish();
    }
}
