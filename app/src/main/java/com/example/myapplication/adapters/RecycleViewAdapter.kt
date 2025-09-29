package com.example.myapplication.adapters

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.myResources.MainButtonsData

class RecycleViewAdapter(
    private val act: FragmentActivity,
    private val icons:Array<MainButtonsData.ButtonInfo>,
    val displayHeight:Float
):RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    class ViewHolder(v: View):RecyclerView.ViewHolder(v){
        val imageBtn = v.findViewById<ImageButton>(R.id.imageBtn)
        val textView = v.findViewById<TextView>(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_grid_item,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageBtn.background = icons[position].background
        holder.imageBtn.setImageResource(icons[position].icons)
        holder.imageBtn.setOnClickListener{v ->
            if(icons[position].destination != null) (act as MainActivity).navController.navigate(icons[position].destination!!)
        }
        holder.textView.text = icons[position].text
        holder.textView.textSize = 16f-((2400f - displayHeight)/400f)
        (holder.textView.parent as? ViewGroup)?.clipChildren = false
        /*TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
            holder.textView,
            12,  // min size in sp
            24,  // max size in sp
            1,   // step granularity in sp
            TypedValue.COMPLEX_UNIT_SP
        )*/
    }

    override fun getItemCount(): Int {
        return icons.size
    }
}