package com.example.myapplication

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class MyGrid(val ctx: Context):RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {

        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        var row = 0
        var rowCnt = 0
        var column = 0
        var colCnt = 0

        if (itemCount == 0) return
        for(i in 0 until itemCount){

            val view = recycler?.getViewForPosition(i)
            addView(view)

            val widthSpec = View.MeasureSpec.makeMeasureSpec(width/4, View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(height/3, View.MeasureSpec.AT_MOST)
            view?.measure(widthSpec, heightSpec)
            val wdth = getDecoratedMeasuredWidth(view!!)
            val hgt = getDecoratedMeasuredHeight(view)
            layoutDecorated(view, column, row, column+wdth, row+hgt)
            if(colCnt == 3){
                column = 0
                row += hgt
                colCnt = 0
            } else {
                column += wdth
                ++colCnt
            }
        }

    }

}