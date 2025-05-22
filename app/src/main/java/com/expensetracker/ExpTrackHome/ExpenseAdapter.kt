package com.expensetracker.ExpTrackHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.expensetracker.R

data class ExpenseItem(
    val date: String,
    val category: String,
    val description: String,
    val amount: String,
    val yourShare: String
)

class ExpenseAdapter(private var expenses: List<ExpenseItem>) : 
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    var onDeleteClickListener: ((Int) -> Unit)? = null

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
        val categoryTxt: TextView = itemView.findViewById(R.id.categoryTxt)
        val thingsTxt: TextView = itemView.findViewById(R.id.thingsTxt)
        val amountTxt: TextView = itemView.findViewById(R.id.amountTxt)
        val yourshareTxt: TextView = itemView.findViewById(R.id.yourshareTxt)
        val deleteBtn: ImageView = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        
        holder.dateTxt.text = expense.date
        holder.categoryTxt.text = expense.category
        holder.thingsTxt.text = expense.description
        holder.amountTxt.text = expense.amount
        holder.yourshareTxt.text = expense.yourShare

        holder.deleteBtn.setOnClickListener {
            onDeleteClickListener?.invoke(position)
        }
    }

    override fun getItemCount() = expenses.size

    fun updateExpenses(newExpenses: List<ExpenseItem>) {
        expenses = newExpenses
        notifyDataSetChanged()
    }
}