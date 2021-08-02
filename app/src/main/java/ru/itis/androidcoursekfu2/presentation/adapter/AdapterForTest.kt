package ru.itis.androidcoursekfu2.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.presentation.models.TestModel

class AdapterForTest(
    private val dataList: List<TestModel>,
    private val click: (HolderForTest) -> Unit
) : RecyclerView.Adapter<AdapterForTest.HolderForTest>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderForTest =
        HolderForTest(
            LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false),
            click
        )

    override fun onBindViewHolder(holder: HolderForTest, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int =
        dataList.size

    inner class HolderForTest(
        containerView: View,
        private val click: (HolderForTest) -> Unit
    ) : RecyclerView.ViewHolder(containerView) {

        private var title: TextView = itemView.findViewById(R.id.item_test_title)

        fun bind(test: TestModel) {
            title.text = test.title

//            itemView.setOnTouchListener { v, event ->
//                if (event.action == MotionEvent.ACTION_UP) {
//                    click(this)
//                }
//                false
//            }

        }

    }
}

