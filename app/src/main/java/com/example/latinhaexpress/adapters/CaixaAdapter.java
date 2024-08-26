package com.example.latinhaexpress.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latinhaexpress.R;
import com.example.latinhaexpress.entities.Caixa;

import java.util.List;

public class CaixaAdapter extends RecyclerView.Adapter<CaixaAdapter.MyViewHolder>
{
    private List<Caixa> caixaList;
    private Context context;

    public CaixaAdapter(List<Caixa> caixaList, Context context)
    {
        this.caixaList = caixaList;
        this.context = context;
    }

    @NonNull
    @Override
    public CaixaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_caixa, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CaixaAdapter.MyViewHolder holder, int position)
    {
      Caixa caixa = caixaList.get(position);

      holder.numeroCaixa.setText(caixa.caixa_id.toString());

    }

    @Override
    public int getItemCount() {
        return caixaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView numeroCaixa, totalCo2, totalColeta, totalVenda;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            numeroCaixa = itemView.findViewById(R.id.numeroCaixa);
            totalCo2 = itemView.findViewById(R.id.totalCo2);
            totalColeta = itemView.findViewById(R.id.totalColeta);
            totalVenda = itemView.findViewById(R.id.totalVenda);
        }
    }
}
