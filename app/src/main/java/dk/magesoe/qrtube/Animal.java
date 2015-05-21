package dk.magesoe.qrtube;

/**
 * Created by FMM on 21-05-2015.
 */
public class Animal {

    private int drawableId;
    private String name;

    public Animal(int drawableId, String name) {
        super();
        this.drawableId = drawableId;
        this.name = name;
    }


    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
