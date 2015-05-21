package dk.magesoe.qrtube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by FMM on 21-05-2015.
 */
public class AnimalIndexAdapter extends BaseAdapter {

    Context context;
    protected List<Animal> animalList;
    LayoutInflater inflater;

    public AnimalIndexAdapter(Context context, List<Animal> animalList) {
        this.animalList = animalList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int position) {
        return animalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return animalList.get(position).getDrawableId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.animalindexitem, parent, false);
            holder.txtName = (TextView) convertView.findViewById(R.id.text);
            holder.imgAnimal = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Animal animal = animalList.get(position);
        holder.txtName.setText(animal.getName());
        holder.imgAnimal.setImageResource(animal.getDrawableId());

        return convertView;
    }

    private class ViewHolder {
        TextView txtName;
        ImageView imgAnimal;
    }
}
