package com.example.myapplication.mainFragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.R
import com.google.android.material.imageview.ShapeableImageView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NeighboursFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_neighbours, container, false)
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val circleImage = view.findViewById<ShapeableImageView>(R.id.circle_image_neighbour)
        val linLay = view.findViewById<LinearLayout>(R.id.linLay_neighbour)
        val backImage = view.findViewById<ImageView>(R.id.back_image_neighbour)

        var xPos = 0f
        var yPos = 0f

        linLay.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN ->{
                    xPos = event.x.toFloat()
                    yPos = event.rawY.toFloat()
                    //Log.d("MyLog", "start: x:${event.x} y:${event.orientation}")
                    //true
                }
                MotionEvent.ACTION_MOVE ->{
                    Log.d("MyLog", "move: x:${yPos-event.rawY}")
                    val params = v.layoutParams as ConstraintLayout.LayoutParams
                    val paramsCircleImg = circleImage.layoutParams as ConstraintLayout.LayoutParams
                    if((yPos - event.rawY) < 0){
                        val f = params.matchConstraintPercentHeight
                        params.matchConstraintPercentHeight -= if(params.matchConstraintPercentHeight > 0.45f)0.005f else 0f
                        backImage.alpha += if(backImage.alpha < 1f)(f-params.matchConstraintPercentHeight)/0.2f else 0f
                        paramsCircleImg.matchConstraintPercentWidth += if(paramsCircleImg.matchConstraintPercentWidth < 0.6f)((f-params.matchConstraintPercentHeight)*0.3f)/0.2f else 0f
                        circleImage.alpha -= if(circleImage.alpha > 0f)(f-params.matchConstraintPercentHeight)/0.2f else 0f
                    }else{
                        val f = params.matchConstraintPercentHeight
                        params.matchConstraintPercentHeight += if(params.matchConstraintPercentHeight < 0.65f)0.005f else 0f
                        backImage.alpha -= if(backImage.alpha > 0f)(params.matchConstraintPercentHeight-f)/0.2f else 0f
                        paramsCircleImg.matchConstraintPercentWidth -= if(paramsCircleImg.matchConstraintPercentWidth > 0.3f)((params.matchConstraintPercentHeight-f)*0.3f)/0.2f else 0f
                        circleImage.alpha += if(circleImage.alpha < 1f)(params.matchConstraintPercentHeight-f)/0.2f else 0f
                    }

                    //Log.d("MyLog", "move: x:${params.verticalBias}")
                    v.layoutParams = params
                    circleImage.layoutParams = paramsCircleImg
                    if(Math.abs(yPos - event.rawY)>5){
                        yPos = event.rawY
                    }

                    //main.requestLayout()
                    //true
                }
                MotionEvent.ACTION_UP ->{
                    val params = v.layoutParams as ConstraintLayout.LayoutParams
                    val paramsCircleImg = circleImage.layoutParams as ConstraintLayout.LayoutParams
                    if(params.matchConstraintPercentHeight > 0.55f){
                        params.matchConstraintPercentHeight = 0.65f
                        backImage.alpha = 0f
                        paramsCircleImg.matchConstraintPercentWidth = 0.3f
                        circleImage.alpha = 1f
                    }else{
                        params.matchConstraintPercentHeight = 0.45f
                        backImage.alpha = 1f
                        paramsCircleImg.matchConstraintPercentWidth = 0.6f
                        circleImage.alpha = 0f
                    }
                    v.layoutParams = params
                    circleImage.layoutParams = paramsCircleImg
                }
                else ->{

                }
            }
            return@setOnTouchListener true
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NeighboursFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}