package ru.itis.androidcoursekfu2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.presentation.adapter.AdapterForTest
import ru.itis.androidcoursekfu2.presentation.models.TestModel

class MotionToolbarActivity : AppCompatActivity() {

    private lateinit var testAdapter: AdapterForTest
    private lateinit var rv: RecyclerView
    private lateinit var image: View
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_toolbar)

        rv = findViewById(R.id.test_recycler)
        image = findViewById(R.id.test_top_image)
        text = findViewById(R.id.test_title)

        testAdapter = AdapterForTest(TestModel.getBigData()) {
            it.itemView.findViewById<MotionLayout>(R.id.item_test_root).apply {
                layoutParams = layoutParams.apply {
                    width = ConstraintLayout.LayoutParams.MATCH_PARENT
                    height = ConstraintLayout.LayoutParams.MATCH_PARENT
                }
            }
        }

        rv.adapter = testAdapter


    }

}