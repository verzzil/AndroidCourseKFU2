package ru.itis.androidcoursekfu2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.widget.Guideline
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.presentation.adapter.DropDownAdapter
import ru.itis.androidcoursekfu2.presentation.models.TestModel
import ru.itis.androidcoursekfu2.utils.awaitTransitionComplete

class MotionToolbarActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private lateinit var motion: MotionLayout
    private lateinit var img: ImageView
    private lateinit var guideline: Guideline
    private lateinit var rv: RecyclerView

    private lateinit var adapter: DropDownAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motion_toolbar)

        motion = findViewById(R.id.motion)
        img = findViewById(R.id.img_view)
        guideline = findViewById(R.id.guideline1)
        rv = findViewById(R.id.rv)

        adapter = DropDownAdapter(TestModel.getBigData()) {
            Log.i("clicked","Ω")
        }

        rv.adapter = adapter

        img.setOnClickListener {
            launch {
                motion.setTransition(R.id.first_set, R.id.second_set)
                motion.transitionToEnd()
                motion.awaitTransitionComplete(R.id.second_set)

                motion.setTransition(R.id.second_set, R.id.third_set)
                motion.transitionToEnd()
                motion.awaitTransitionComplete(R.id.third_set)

                motion.setTransition(R.id.third_set, R.id.fourth_set)
                motion.transitionToEnd()
                motion.awaitTransitionComplete(R.id.fourth_set)

                motion.setTransition(R.id.fourth_set, R.id.fifth_set)
                motion.transitionToEnd()
            }
        }



    }

}