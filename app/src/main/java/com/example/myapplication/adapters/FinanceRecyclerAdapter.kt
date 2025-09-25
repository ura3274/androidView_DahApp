package com.example.myapplication.adapters

import com.example.myapplication.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.myResources.FinanceButtonsData

class FinanceRecyclerAdapter(val buttonsData: Array<FinanceButtonsData.ButtonInfo>): RecyclerView.Adapter<FinanceRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_finance, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btn.background = buttonsData[0].background
        holder.btn.setImageResource(buttonsData[0].icon)
        holder.text.text = buttonsData[0].title
    }

    override fun getItemCount(): Int {
        return 3
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val btn = v.findViewById<ImageButton>(R.id.imageBtn_finance)
        val text = v.findViewById<TextView>(R.id.textView_finance)
    }
}