package com.example.latinhaexpress.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.entities.Venda;

import java.util.List;

public class VendaAdapter  extends RecyclerView.Adapter<VendaAdapter.MyViewHolder>
{

    List<Venda> vendaList;
    private Context context;

    public VendaAdapter(List<Venda> vendaList, Context context)
    {
        this.vendaList = vendaList;
        this.context = context;
    }

    @NonNull
    @Override
    public VendaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_venda, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VendaAdapter.MyViewHolder holder, int position)
    {
        Venda venda = vendaList.get(position);

        holder.recicladora.setText(venda.venda_recicladora_nome.toString());
        holder.qtde.setText(String.format("%.2f", venda.venda_qtde));
        holder.co2.setText(String.format("%.2f",venda.venda_qtde_co2) + "T");
        holder.precoVenda.setText(String.format("%.2f", venda.venda_preco_total));
        holder.total.setText(String.format("%.2f", venda.venda_preco_total * venda.venda_qtde));
    }

    @Override
    public int getItemCount()
    {
        return vendaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView recicladora, co2, qtde, total, precoVenda;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            recicladora = itemView.findViewById(R.id.nomeRecicladora);
            co2 = itemView.findViewById(R.id.totalCo2);
            qtde = itemView.findViewById(R.id.qtde);
            total = itemView.findViewById(R.id.totalVenda);
            precoVenda = itemView.findViewById(R.id.precoVenda);
        }
    }
}
