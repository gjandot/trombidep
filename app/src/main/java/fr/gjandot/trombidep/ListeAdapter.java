package fr.gjandot.trombidep;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class ListeAdapter extends BaseAdapter  implements Filterable {
    private Context context;

    private List<Depute> listDeputes;
    private List<Depute> tousDeputes;
    private Filter mFilter;
    public ImageLoader imageLoader;

    public ListeAdapter(Context context, List<Depute> listIn) {
        this.context = context;
        this.listDeputes = listIn;
        this.tousDeputes = this.listDeputes;
        this.getFilter().filter("");
        imageLoader=new ImageLoader(context);
    }

    public int getCount() {
    	if (listDeputes==null)
    	{
    		return 0;
    	}
    	else
    	{
    		return listDeputes.size();
    	}
    }

    public Object getItem(int position) {
        return listDeputes.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ImageView maVue;
        Depute entry = listDeputes.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.eltitem, null);
        }
        TextView tvNom = (TextView) convertView.findViewById(R.id.ELTtitre);
        tvNom.setText(entry.getNom());
        TextView tvGroupe = (TextView) convertView.findViewById(R.id.depgroupe);
        tvGroupe.setText(entry.getGrp());
        TextView tvCirco = (TextView) convertView.findViewById(R.id.depcirco);
        //tvCirco.setText(entry.getCirco());
        tvCirco.setText(entry.getLongCirco());
        maVue = (ImageView) convertView.findViewById(R.id.imageView);
        if (entry.isSexe_H())
        {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colHomme));
        }
        else
        {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.colFemme));
        }
        convertView.setTag(entry.getDepUrl());

        if (maVue != null) {
            imageLoader.DisplayImage(context.getResources().getString(R.string.url_photo) + entry.getImgUrl() + "/64", maVue);
        }

        return convertView;
    }
    
    @Override
    public Filter getFilter() {
         if (mFilter == null) {
                mFilter = new FiltreParlement();
            }
        return mFilter;
    }
    
    
    private class FiltreParlement extends Filter {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listDeputes = (List<Depute>) results.values;
                notifyDataSetChanged();
            }

        @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();
                ArrayList<Depute> filtreDeputes = new  ArrayList<Depute>();

            if (DepList.filtreGroupe == null)
            {
                return results;
            }

            for (int index = 0; index < tousDeputes.size(); index++) {
                Depute dep = tousDeputes.get(index);
                boolean ajout = false;
                if (DepList.vueH && dep.isSexe_H()) {
                    ajout = true;
                }
                if (DepList.vueF && !dep.isSexe_H()) {
                    ajout = true;
                }
                if ((!DepList.filtreGroupe.contentEquals(context.getResources().getString(R.string.tous))) && (!dep.getGrp().contentEquals(DepList.filtreGroupe)))
                {
                    ajout=false;
                }
                if (ajout) {
                    if (constraint != null && constraint.toString().length() > 0) {
                        if (dep.getNom().charAt(0) != '-') {
                            if (dep.getNom().toUpperCase().contains(constraint.toString().toUpperCase())) {
                                filtreDeputes.add(dep);
                            }
                        }
                    }
                    else {
                        filtreDeputes.add(dep);
                    }
                }
            }
        results.values = filtreDeputes;
        results.count = filtreDeputes.size();
        return results;
        }
    }

    void clearImageCache()
    {
        imageLoader.clearCache();
    }
}

