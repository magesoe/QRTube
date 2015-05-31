package dk.magesoe.qrtube;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;

import java.util.ArrayList;

/**
 * Created by FMM on 26-05-2015.
 */
public class AnimalDatabase {

    private ArrayList<Animal> animals;

    public AnimalDatabase(Context context) {
        animals = new ArrayList<Animal>();

        // animals
        Animal penguin = new Animal(R.drawable.penguin, "penguin", "Humbolt Pingvin", context.getResources().getString(R.string.peng_desc),  "Sydamerika", "4-5 kg", Html.fromHtml("Fisk og bl&#230;ksprutter").toString());
        Animal surikat = new Animal(R.drawable.surikat, "surikat", "Surikat", context.getResources().getString(R.string.suri_desc),  "Det sydligste Afrika", "N/A", Html.fromHtml("Alle sm&#229;dyr").toString());
        Animal kattalemur = new Animal(R.drawable.kattalemur, "kattalemur", "Kattalemur",context.getResources().getString(R.string.kata_desc)
                ,
                "Madagaskar",
                "2,3 - 3,5 kg",
                "Frugt, insekter");

        animals.add(penguin);
        animals.add(surikat);
        animals.add(kattalemur);
    }

    public ArrayList<Animal> getAnimalList() {
        return animals;
    }


}
