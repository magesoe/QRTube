package dk.magesoe.qrtube;

/**
 * Created by FMM on 21-05-2015.
 */
public class Animal {

    private int drawableId;
    private String name;
    private String desc;
    private String height;
    private String habitat;
    private String weight;
    private String eats;

    public Animal(int drawableId, String name, String desc, String height, String habitat, String weight, String eats) {
        super();
        this.drawableId = drawableId;
        this.name = name;
        this.desc = desc;
        this.height = height;
        this.habitat = habitat;
        this.weight = weight;
        this.eats = eats;
    }


    public int getDrawableId() {
        return drawableId;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getHeight() {
        return height;
    }

    public String getHabitat() {
        return habitat;
    }

    public String getWeight() {
        return weight;
    }

    public String getEats() {
        return eats;
    }
}
