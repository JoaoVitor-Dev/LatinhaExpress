package com.example.latinhaexpress.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latinhaexpress.R;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.entities.Coleta;

import java.util.List;

public class ColetaAdapter extends RecyclerView.Adapter<ColetaAdapter.MyViewHolder>
{
    private List<Coleta> coletaList;
    private Context context;

    public ColetaAdapter(List<Coleta> coletaList, Context context)
    {
        this.coletaList = coletaList;
        this.context = context;
    }

    @NonNull
    @Override
    public ColetaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coleta, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ColetaAdapter.MyViewHolder holder, int position)
    {
        Coleta coleta = coletaList.get(position);

        holder.vendedor.setText(coleta.coleta_vendedor_nome.toString());
        holder.obs.setText(coleta.coleta_obs.toString());
        holder.qtde.setText(String.valueOf(coleta.coleta_qtde));
        holder.valor.setText(String.valueOf(coleta.coleta_preco * coleta.coleta_qtde));
    }

    @Override
    public int getItemCount()
    {
        return coletaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView vendedor, qtde, valor, obs;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            vendedor =  itemView.findViewById(R.id.nomeVendedor);
            qtde = itemView.findViewById(R.id.qtde);
            valor = itemView.findViewById(R.id.totalColeta);
            obs = itemView.findViewById(R.id.obs);
        }
    }
}
