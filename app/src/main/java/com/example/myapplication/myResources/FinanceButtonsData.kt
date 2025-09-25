package com.example.myapplication.myResources

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.drawable.shapes.Shape
import com.example.myapplication.R

class FinanceButtonsData (private val ctx: Context, private val displayHeight:Float) {
    val data:Array<ButtonInfo>

    init {
        data = arrayOf(ButtonInfo(
            title = "Утримання будинку",
            total = 0.0f,
            icon = R.drawable.bottom_nav_home_24,
            background = setBackground(intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800)
        ))
    }

    private fun setBackground(colors:IntArray, ratio:Float):RippleDrawable{
        val grad = GradientDrawable(
            GradientDrawable.Orientation.BL_TR,
            colors
        ).apply {
            gradientType = GradientDrawable.LINEAR_GRADIENT
            setShape(GradientDrawable.RECTANGLE)
            setCornerRadius(15f*ratio)
        }
        val shape = ShapeDrawable(
            RoundRectShape(
            floatArrayOf(15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio),null,null
        )
        ).apply{
            paint.style = Paint.Style.FILL
            paint.color = Color.WHITE
        }
        return RippleDrawable(
            ColorStateList.valueOf(Color.WHITE),
            grad,
            shape
        )
    }

    data class ButtonInfo(
        val title: String,
        val total: Float,
        val icon: Int,
        val background: Drawable
    )
}