package com.skysoftsolution.thingisbeing.dailyRoutine

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.skysoftsolution.thingisbeing.R
import com.skysoftsolution.thingisbeing.dailyRoutine.model.DailyRoutineTask
import com.skysoftsolution.thingisbeing.dailyRoutine.model.DailyRoutineViewModel
import com.skysoftsolution.thingisbeing.databinding.ActivityDailyRoutineBinding
import com.skysoftsolution.thingisbeing.datasource.DataAccessObj
import com.skysoftsolution.thingisbeing.datasource.DataBaseCreator
import com.skysoftsolution.thingisbeing.repository.MainRepository
import com.skysoftsolution.thingisbeing.viewmodelfactory.MainViewModelFatcory

class DailyRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDailyRoutineBinding
    private lateinit var adapter: AdapterForAddTask
    private lateinit var viewModel: DailyRoutineViewModel
    lateinit var dataAccessObj: DataAccessObj
    private var dailTaskList = ArrayList<DailyRoutineTask>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataAccessObj = DataBaseCreator.getInstance(this@DailyRoutineActivity).dataAccessObj
        viewModel = ViewModelProvider(
            this, MainViewModelFatcory(MainRepository(dataAccessObj), application)
        )[DailyRoutineViewModel::class.java]
        binding.lifecycleOwner = this
        viewModelCallerMethod()




         binding.fab.setOnClickListener{
             showAddNoteDialog()
         }
    }
    private fun showAddNoteDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogLayout = inflater.inflate(R.layout.dialog_add_note, null)
        val editTextTitle = dialogLayout.findViewById<EditText>(R.id.titleEditText)
        val editTextContent = dialogLayout.findViewById<EditText>(R.id.contentEditText)

        builder.setView(dialogLayout)
            .setTitle("Add Sticky Note")
            .setPositiveButton("Save") { dialog, _ ->
                val title = editTextTitle.text.toString()
                val content = editTextContent.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    val newNote = DailyRoutineTask(
                        title = title,
                        content = content
                    )
                  //  viewModel.insert(newNote)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun showEditNoteDialog(dailyRoutineTask: DailyRoutineTask) {
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogLayout = inflater.inflate(R.layout.dialog_add_note, null)
        val editTextTitle = dialogLayout.findViewById<EditText>(R.id.titleEditText)
        val editTextContent = dialogLayout.findViewById<EditText>(R.id.contentEditText)

        editTextTitle.setText(dailyRoutineTask.title)
        editTextContent.setText(dailyRoutineTask.content)

        builder.setView(dialogLayout)
            .setTitle("Edit Sticky Note")
            .setPositiveButton("Update") { dialog, _ ->
                val title = editTextTitle.text.toString()
                val content = editTextContent.text.toString()
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    val updatedNote = dailyRoutineTask.copy(
                        title = title,
                        content = content
                    )
                  //  viewModel.update(updatedNote)
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
    private fun viewModelCallerMethod() {
        viewModel.getDailyTaskDetails()
            ?.observe(this@DailyRoutineActivity) {
                if (it != null) {
                    if (it.size!! > 0) {
                        dailTaskList = it as ArrayList<DailyRoutineTask>
                        setAdapterForView(dailTaskList)
                    }
                }
            }

    }

    private fun setAdapterForView(dailTaskList: ArrayList<DailyRoutineTask>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.setNotes(dailTaskList)
    }


    private fun setupRecyclerView() {
        adapter = AdapterForAddTask(
            editNote = { note -> showEditNoteDialog(note) },
            deleteNote = { note -> viewModel.delete(note) }
        )
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.fab_margin)
        binding.recyclerView.addItemDecoration(SpacesItemDecoration(spacingInPixels))


    }
}