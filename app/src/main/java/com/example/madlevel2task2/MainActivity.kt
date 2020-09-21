package com.example.madlevel2task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.MainActivityBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initViews()
    }

    fun initViews() {
        questions.add(Question("A 'val' and 'Var' are the same", false))
        questions.add(Question("MAD grants 12 ECTS", true))
        questions.add(Question("A Unit in Kotlin corresponds to a void in Java", false))
        questions.add(Question("In Kotlin 'when' replaces the 'switch' operator in Java", true))
        binding.rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter


        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, 12) {
            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if(direction == ItemTouchHelper.RIGHT && questions[position].answer) {
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                }else if(direction == ItemTouchHelper.LEFT && !questions[position].answer) {
                    questions.removeAt(position)
                    questionAdapter.notifyDataSetChanged()
                }else{
                    questionAdapter.notifyDataSetChanged()
                    val snackbar = Snackbar.make(binding.mainLayoutId, "Your answer was wrong!!", Snackbar.LENGTH_SHORT);
                    snackbar.show()
                }
            }
        }
        return ItemTouchHelper(callback)
    }
}