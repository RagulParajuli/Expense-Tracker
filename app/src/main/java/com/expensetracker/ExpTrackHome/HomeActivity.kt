package com.expensetracker.ExpTrackHome

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.expensetracker.R
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class HomeActivity : AppCompatActivity() {
    private lateinit var homeImg: ImageView
    private lateinit var homeTxt: TextView
    private lateinit var analysisImg: ImageView
    private lateinit var analysisTxt: TextView
    private lateinit var spendingImg: ImageView
    private lateinit var spendingTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

//        code for piechart
        val pieChart = findViewById<PieChart?>(R.id.chart)

        val entiers = ArrayList<PieEntry?>()
        entiers.add(PieEntry(80f, "Food"))
        entiers.add(PieEntry(90f, "Fuel"))
        entiers.add(PieEntry(75f, "Grocery"))
        entiers.add(PieEntry(60f, "Sopping"))
        entiers.add(PieEntry(30f, "Travel"))
        entiers.add(PieEntry(20f, "Fun"))

        val pieDataSet = PieDataSet(entiers, "Subjects")
        pieDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

        val pieData = PieData(pieDataSet)
        pieChart.setData(pieData)

        pieChart.getDescription().setEnabled(false)
        pieChart.animateY(1000)
        pieChart.invalidate()


        // Initialize views
        initializeViews()
        
        // Set click listeners
        setupClickListeners()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeViews() {
        homeImg = findViewById(R.id.homeImg)
        homeTxt = findViewById(R.id.homeTxt)
        analysisImg = findViewById(R.id.analysisImg)
        analysisTxt = findViewById(R.id.analysisTxt)
//        spendingImg = findViewById(R.id.spendingImg)
//        spendingTxt = findViewById(R.id.spendingTxt)
    }

    private fun setupClickListeners() {
        // Home click listeners
        homeImg.setOnClickListener {
            // Already in HomeActivity, no need to navigate
        }
        homeTxt.setOnClickListener {
            // Already in HomeActivity, no need to navigate
        }

        // Analysis click listeners
        analysisImg.setOnClickListener {
            startActivity(Intent(this, AnalysisActivity::class.java))
        }
        analysisTxt.setOnClickListener {
            startActivity(Intent(this, AnalysisActivity::class.java))
        }

//        // Spending click listeners
//        spendingImg.setOnClickListener {
//            startActivity(Intent(this, SpendingActivity::class.java))
//        }
//        spendingTxt.setOnClickListener {
//            startActivity(Intent(this, SpendingActivity::class.java))
//        }
    }
}