package br.com.mattsousa.minhagrandefamilia.gof;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.mattsousa.minhagrandefamilia.R;
import br.com.mattsousa.minhagrandefamilia.model.Relative;

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.ViewHolder>{
    private ArrayList<Relative> relatives;

    public TreeAdapter(ArrayList<Relative> relatives) {
        this.relatives = relatives;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_birthday_relative, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(relatives.get(i).getPhoto(), 0,
                relatives.get(i).getPhoto().length);
        holder.imvwPhoto.setImageBitmap(bitmap);
        holder.txwName.setText(relatives.get(i).getName());
        holder.txwParentage.setText(relatives.get(i).getParentage().getRelationName());
        holder.txwBirthday.setText(relatives.get(i).getPrettyDate());
    }

    @Override
    public int getItemCount() {
        return relatives.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txwName, txwParentage, txwBirthday;
        ImageView imvwPhoto;
        ViewHolder(View view) {
            super(view);
            txwName = (TextView)itemView.findViewById(R.id.birthday_txw_name);
            txwParentage = (TextView)itemView.findViewById(R.id.birthday_txw_parentage);
            txwBirthday = (TextView)itemView.findViewById(R.id.birthday_txw_birthday);
            imvwPhoto = (ImageView) itemView.findViewById(R.id.birthday_imvw_photo);
        }
    }
}
