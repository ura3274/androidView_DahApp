package com.example.myapplication.mainFragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.FinanceRecyclerAdapter
import com.example.myapplication.myResources.FinanceButtonsData
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FinanceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finance, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val displayHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()
        val frontView = view.findViewById<View>(R.id.frontConstraint_finance)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_finance)
        val buttonPay = view.findViewById<MaterialButton>(R.id.buttonPay_finance)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val nav = findNavController()
            nav.popBackStack()
        }

        recycler.adapter = FinanceRecyclerAdapter(FinanceButtonsData(requireContext(), displayHeight).data)
        buttonPay.backgroundTintList = null

        var xPos = 0f
        var yPos = 0f
        recycler.setOnTouchListener { v, event ->
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
                    if((yPos - event.rawY) < 0){
                        val f = params.matchConstraintPercentHeight
                        params.matchConstraintPercentHeight -= if(params.matchConstraintPercentHeight > 0.65f)0.005f else 0f
                        frontView.alpha += if(frontView.alpha < 1f)(f-params.matchConstraintPercentHeight)/0.2f else 0f
                    }else{
                        val f = params.matchConstraintPercentHeight
                        params.matchConstraintPercentHeight += if(params.matchConstraintPercentHeight < 0.85f)0.005f else 0f
                        frontView.alpha -= if(frontView.alpha > 0f)(params.matchConstraintPercentHeight-f)/0.2f else 0f
                    }

                    //Log.d("MyLog", "move: x:${params.verticalBias}")
                    v.layoutParams = params
                    if(Math.abs(yPos - event.rawY)>5){
                        yPos = event.rawY
                    }

                    //main.requestLayout()
                    //true
                }
                MotionEvent.ACTION_UP ->{
                    val params = v.layoutParams as ConstraintLayout.LayoutParams
                    if(params.matchConstraintPercentHeight > 0.75f){
                        params.matchConstraintPercentHeight = 0.85f
                        frontView.alpha = 0f
                    }else{
                        params.matchConstraintPercentHeight = 0.65f
                        frontView.alpha = 1f
                    }

                    v.layoutParams = params
                }
                else ->{

                }
            }
            return@setOnTouchListener true
        }
    }

    private fun setBackground(
        m_colors: IntArray,
        m_orientation:GradientDrawable.Orientation,
        m_shape:Int,
        m_cornerRadius:Float
    ){
        val shape = GradientDrawable(m_orientation, m_colors)
        shape.shape = m_shape
        shape.cornerRadius = m_cornerRadius
    }
}