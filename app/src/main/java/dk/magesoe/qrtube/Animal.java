package dk.magesoe.qrtube;

/**
 * Created by FMM on 21-05-2015.
 */
public class Animal {

    private int drawableId;
    private String videoname;
    private String name;
    private String desc;
    private String habitat;
    private String weight;
    private String eats;

    public Animal(int drawableId, String videoname, String name, String desc, String habitat, String weight, String eats) {
        super();
        this.drawableId = drawableId;
        this.videoname = videoname;
        this.name = name;
        this.desc = desc;
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

    public String getHabitat() {
        return habitat;
    }

    public String getWeight() {
        return weight;
    }

    public String getEats() {
        return eats;
    }

    public String getVideoname() {
        return videoname;
    }
}
