package ru.itis.androidcoursekfu2.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnLayout
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.presentation.models.TestModel

class DropDownAdapter(
    val list: List<TestModel>,
    val click: (TestModel) -> Unit
) : RecyclerView.Adapter<DropDownAdapter.DropDownHolder>() {

    private var originalHeight = -1
    private var expandHeight = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropDownHolder =
        DropDownHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false),
            click
        )

    override fun onBindViewHolder(holder: DropDownHolder, position: Int) {
        holder.bind(list[position])
        Log.i("callbacksrecycler","onBindViewHolder $holder")
    }

    override fun onViewAttachedToWindow(holder: DropDownHolder) {
        super.onViewAttachedToWindow(holder)
        Log.i("callbacksrecycler","onViewAttachedToWindow ${holder}")

        holder.itemView.doOnLayout {
            it.findViewById<TextView>(R.id.item_test_description).visibility = View.VISIBLE

            it.doOnPreDraw {
                it.requestLayout()
                Log.i("callbacksrecycler", "expandedHeight ${it.height}")
            }
        }
    }

    override fun getItemCount(): Int =
        list.size

    inner class DropDownHolder(
        containerView: View,
        val click: (TestModel) -> Unit
    ) : RecyclerView.ViewHolder(containerView) {

        private var title: TextView = itemView.findViewById(R.id.item_test_title)
        private var desc: TextView = itemView.findViewById(R.id.item_test_description)

        fun bind(model: TestModel) {
            title.text = model.title
            desc.text = model.description

            itemView.setOnClickListener {
                click(model)
            }
        }

    }
}