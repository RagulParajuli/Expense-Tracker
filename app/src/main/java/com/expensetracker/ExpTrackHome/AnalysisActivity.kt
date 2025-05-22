package com.expensetracker.ExpTrackHome

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.expensetracker.ExpTrackHome.ExpenseAdapter
import com.expensetracker.ExpTrackHome.ExpenseItem
import com.expensetracker.R

class AnalysisActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navToday: TextView
    private lateinit var navMonth1: TextView
    private lateinit var navMonth2: TextView
    private lateinit var navMonth3: TextView
    private var selectedNavItem: TextView? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var expenseAdapter: ExpenseAdapter
    private val expensesList = mutableListOf<ExpenseItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis)

        // Initialize views
        drawerLayout = findViewById(R.id.drawer_layout)
        navToday = findViewById(R.id.nav_today)
        navMonth1 = findViewById(R.id.nav_1month)
        navMonth2 = findViewById(R.id.nav_2month)
        navMonth3 = findViewById(R.id.nav_3month)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.expensesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        expenseAdapter = ExpenseAdapter(expensesList)
        recyclerView.adapter = expenseAdapter

        // Set up delete click listener
        expenseAdapter.onDeleteClickListener = { position ->
            expensesList.removeAt(position)
            expenseAdapter.updateExpenses(expensesList)
        }

        // Initialize bottom navigation views
        val homeImg = findViewById<ImageView>(R.id.homeImg)
        val homeTxt = findViewById<TextView>(R.id.homeTxt)
        val analysisImg = findViewById<ImageView>(R.id.analysisImg)
        val analysisTxt = findViewById<TextView>(R.id.analysisTxt)

        // Set click listeners for bottom navigation
        homeImg.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        homeTxt.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Analysis is current activity, no need to navigate

        // Set click listeners for navigation items
        navToday.setOnClickListener {
            // Handle Today selection
            updateSelectedNavItem(navToday)
            updateTimePeriod("Today")
            closeDrawer()
        }

        navMonth1.setOnClickListener {
            // Handle Month 1 selection
            updateSelectedNavItem(navMonth1)
            updateTimePeriod("Month 1")
            closeDrawer()
        }

        navMonth2.setOnClickListener {
            // Handle Month 2 selection
            updateSelectedNavItem(navMonth2)
            updateTimePeriod("Month 2")
            closeDrawer()
        }

        navMonth3.setOnClickListener {
            // Handle Month 3 selection
            updateSelectedNavItem(navMonth3)
            updateTimePeriod("Month 3")
            closeDrawer()
        }

        // Set initial selection
        updateSelectedNavItem(navToday)
    }

    fun ic_bar(view: View) {
        // Open drawer when menu icon is clicked
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun closeDrawer() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun updateTimePeriod(period: String) {
        // Update expenses list based on selected time period
        expensesList.clear()
        when (period) {
            "Today" -> loadTodayExpenses()
            "Month 1" -> loadMonthExpenses(1)
            "Month 2" -> loadMonthExpenses(2)
            "Month 3" -> loadMonthExpenses(3)
        }
        expenseAdapter.updateExpenses(expensesList)
    }
    
    private fun loadTodayExpenses() {
        // TODO: Implement loading today's expenses from database
        // For now, adding sample data
        expensesList.add(ExpenseItem("Today", "Food", "Lunch", "Rs.250", "Your share"))
    }

    private fun loadMonthExpenses(monthsAgo: Int) {
        // TODO: Implement loading expenses for specific month from database
        // For now, adding sample data
        if(monthsAgo==1){
        expensesList.add(ExpenseItem("May ${20-monthsAgo}", "Shopping", "Groceries", "Rs.1000", "Your share"))
        expensesList.add(ExpenseItem("May ${15-monthsAgo}", "Transport", "Uber", "Rs.150", "Your share"))
        }
        else if (monthsAgo == 2){
            expensesList.add(ExpenseItem("May ${20-monthsAgo}", "Fuel", "Petrol", "Rs.1000", "Your share"))
            expensesList.add(ExpenseItem("May ${15-monthsAgo}", "Food", "fried rice", "Rs.80", "Your share"))
        }
        else{
            expensesList.add(ExpenseItem("May ${20-monthsAgo}", "Food", "samosa", "Rs.20", "Your share"))
            expensesList.add(ExpenseItem("May ${15-monthsAgo}", "Transport", "bus", "Rs.100", "Your share"))

        }
    }

    private fun updateSelectedNavItem(selectedItem: TextView) {
        // Reset previous selection
        selectedNavItem?.setBackgroundResource(android.R.color.transparent)
        selectedNavItem?.setTextColor(resources.getColor(android.R.color.white, theme))

        // Update new selection
        selectedItem.setBackgroundResource(R.drawable.nav_item_selected_background)
        selectedItem.setTextColor(resources.getColor(R.color.orange, theme))
        selectedNavItem = selectedItem
    }
}