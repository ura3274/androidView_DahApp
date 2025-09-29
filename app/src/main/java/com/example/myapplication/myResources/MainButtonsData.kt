package com.example.myapplication.myResources

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.myapplication.R

class MainButtonsData(private val ctx: Context, private val displayHeight:Float) {
    val data:Array<ButtonInfo>

    init{
        Log.d("Mylog", "${displayHeight}")
        data  = arrayOf<ButtonInfo>(
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[0],
                icons = R.drawable.main_btn_primishenia_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[1],
                icons = R.drawable.main_btn_pravlinia_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[2],
                icons = R.drawable.main_btn_susidy_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = R.id.toNeighboursFragmentAction
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[3],
                icons = R.drawable.main_btn_dovidkova_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[4],
                icons = R.drawable.main_btn_vneski_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[5],
                icons = R.drawable.main_btn_lichilinyk_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[6],
                icons = R.drawable.main_btn_moiplateji_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[7],
                icons = R.drawable.main_btn_finansy_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = R.id.toFinanceFragmentAction
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[8],
                icons = R.drawable.main_btn_documenty24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[9],
                icons = R.drawable.main_btn_zaiavki_24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, Color.YELLOW), this.displayHeight /800
                ),
                destination = null
            ),
            ButtonInfo(
                text = ctx.resources.getStringArray(R.array.main_buttons)[10],
                icons = R.drawable.main_btn_zbory24,
                background = setDrawable(
                    intArrayOf(Color.BLACK, ContextCompat.getColor(ctx, R.color.my_color)), this.displayHeight /800
                ),
                destination = null
            )
        )
    }
    private fun setDrawable(colors:IntArray, ratio:Float): RippleDrawable {
        val grad = GradientDrawable(
            GradientDrawable.Orientation.BL_TR,
            colors
        ).apply{
            gradientType = GradientDrawable.LINEAR_GRADIENT
            setShape(GradientDrawable.RECTANGLE)
            setCornerRadius(15f*ratio)
        }
        val shape = ShapeDrawable(RoundRectShape(
            floatArrayOf(15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio,15f*ratio),null,null
        )).apply{
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
        val text:String,
        val icons:Int,
        val background: Drawable,
        val destination: Int?
    )
}