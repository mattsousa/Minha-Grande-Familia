package br.com.mattsousa.minhagrandefamilia.gof;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.mattsousa.minhagrandefamilia.R;
import br.com.mattsousa.minhagrandefamilia.activity.MainActivity;
import br.com.mattsousa.minhagrandefamilia.activity.NewRelativeActivity;
import br.com.mattsousa.minhagrandefamilia.activity.TreeActivity;
import br.com.mattsousa.minhagrandefamilia.dao.RelativeDAO;
import br.com.mattsousa.minhagrandefamilia.model.Relative;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<Relative> relatives;
    private Activity activity;

    public MainAdapter(ArrayList<Relative> relatives, Activity activity) {
        this.relatives = relatives;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recycler_global_relative, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int i) {
        if(relatives.get(i).getPhoto() == null){
            relatives.get(i).setPhoto(new byte[0]);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(relatives.get(i).getPhoto(), 0,
                relatives.get(i).getPhoto().length);

        holder.imvwPhoto.setImageBitmap(bitmap);
        holder.txwName.setText(relatives.get(i).getName());
        holder.txwParentage.setText(relatives.get(i).getParentage().getRelationName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(activity instanceof TreeActivity){
                    Intent it = new Intent(activity.getApplicationContext(), NewRelativeActivity.class);
                    Gson gson = new Gson();
                    it.putExtra("relative",gson.toJson(relatives.get(i)));
                    it.putExtra("newUser",false);
                    activity.startActivity(it);
                }
                else
                    createDialog(view, relatives.get(i));
            }
        });

    }
    @Override
    public int getItemCount() {
        return relatives.size();
    }

    // Create dialog on touch one of the items
    private void createDialog(View view, final Relative relative){
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        final AlertDialog alertDialog;
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_main_relative, null);
        Bitmap bitmap = BitmapFactory.decodeByteArray(relative.getPhoto(), 0,
                relative.getPhoto().length);
        Button
                btnRemove = dialogView.findViewById(R.id.main_btn_remove),
                btnEdit = dialogView.findViewById(R.id.main_btn_edit);
        TextView txwName = dialogView.findViewById(R.id.main_txw_name)
                ,txwBirthday = dialogView.findViewById(R.id.main_txw_birthday)
                ,txwParentage = dialogView.findViewById(R.id.main_txw_parentage);
        ImageView imvPhoto = dialogView.findViewById(R.id.main_imvw_photo);

        txwName.setText(relative.getName());
        txwBirthday.setText(relative.getPrettyDate());
        txwParentage.setText(relative.getParentage().getRelationName());
        imvPhoto.setImageBitmap(bitmap);

        builder.setTitle(R.string.main_dialog_title);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.show();
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(activity.getApplicationContext(), MainActivity.class);
                alertDialog.dismiss();
                RelativeDAO.removeById(relative.getId());
                activity.startActivity(it);
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(activity.getApplicationContext(), NewRelativeActivity.class);
                Gson gson = new Gson();
                it.putExtra("relative",gson.toJson(relative));
                it.putExtra("newUser",false);
                alertDialog.dismiss();
                activity.startActivity(it);
            }
        });
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txwName, txwParentage;
        ImageView imvwPhoto;
        View view;
        ViewHolder(View view) {
            super(view);
            txwName = (TextView)itemView.findViewById(R.id.global_txw_name);
            txwParentage = (TextView)itemView.findViewById(R.id.global_txw_parentage);
            imvwPhoto = (ImageView) itemView.findViewById(R.id.global_imvw_photo);
            this.view = view;
        }
    }

}
