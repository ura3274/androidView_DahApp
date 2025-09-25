package com.example.myapplication.mainFragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.MyGrid
import com.example.myapplication.R
import com.example.myapplication.adapters.RecycleViewAdapter
import com.example.myapplication.adapters.ViewPagerAdapter
import com.example.myapplication.myResources.MainButtonsData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.DEBUG_PROPERTY_NAME

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val displayHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        viewPager.adapter = ViewPagerAdapter()
        TabLayoutMediator(tabLayout, viewPager){tab, pos ->
            tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.tab_layout_icon)
            //tab.setText("hhhhhhhh")
            //tab.icon = Resources.getSystem().getDrawable(R.drawable.tab_layout_icon, null)
        }.attach()

        //GridLayoutManager(requireContext(),4, RecyclerView.VERTICAL, false)
        recyclerView.adapter = RecycleViewAdapter(
            requireActivity(),
            MainButtonsData(requireContext(), displayHeight).data,
            displayHeight
        )
        recyclerView.layoutManager = MyGrid(requireContext())
    }



}