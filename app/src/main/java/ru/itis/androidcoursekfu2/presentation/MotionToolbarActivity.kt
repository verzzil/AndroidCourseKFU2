package ru.itis.androidcoursekfu2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.presentation.adapter.AdapterForTest
import ru.itis.androidcoursekfu2.presentation.models.TestModel

class MotionToolbarActivity : AppCompatActivity() {

    private lateinit var motion: MotionLayout
    private lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_toolbar)

        motion = findViewById(R.id.motion)
        img = findViewById(R.id.img_view)

        img.setOnClickListener {
            motion.transitionToState(R.id.second_set)
        }

    }

}