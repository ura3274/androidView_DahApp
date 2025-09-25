package com.example.myapplication

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class MySwitch @JvmOverloads constructor(ctx:Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0):FrameLayout(ctx,
    attrs, defStyleAttr) {
    private val track:ConstraintLayout?
    private val thumb:ImageView?
    var params:ConstraintLayout.LayoutParams? = null

    init{
        this.clipChildren = false
        LayoutInflater.from(ctx).inflate(R.layout.my_switch,this, true)

        track = findViewById<ConstraintLayout>(R.id.mySwitch)
        thumb = findViewById<ImageView>(R.id.thumbSwitch)
        params = thumb.layoutParams as ConstraintLayout.LayoutParams
        track.setOnClickListener{v->
            animateThumb().start()
            //params?.horizontalBias = if(params?.horizontalBias == 0f)1f else 0f
            //thumb.layoutParams = params
        }
    }

    private fun animateThumb(): ValueAnimator{
        return ValueAnimator.ofFloat(params!!.horizontalBias, if(params!!.horizontalBias == 0f)1f else 0f).apply{
            duration = 1000
            addUpdateListener {animation ->
                params?.horizontalBias = animation.animatedValue as Float
                thumb?.layoutParams = params
            }
        }


    }

}