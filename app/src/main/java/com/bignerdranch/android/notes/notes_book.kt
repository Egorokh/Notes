package com.bignerdranch.android.notes

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.notes.Adaptor.NotesAdaptor
import com.bignerdranch.android.notes.DataBase.EntityDataBase
import com.bignerdranch.android.notes.DataBase.NotesDataBase
import com.bignerdranch.android.notes.DataBase.NotesViewModel
import com.bignerdranch.android.notes.R
import com.bignerdranch.android.notes.databinding.NotesBookBinding
import com.bignerdranch.android.notes.databinding.SignInBinding
import com.bignerdranch.android.notes.databinding.SignUpBinding
import com.google.firebase.auth.FirebaseAuth

class notes_book : Fragment(), NotesAdaptor.NoteClickListener{
        private lateinit var binding: NotesBookBinding
        private lateinit var database: NotesDataBase
        lateinit var viewModel: NotesViewModel
        lateinit var adapter: NotesAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NotesBookBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
            ).get(NotesViewModel::class.java)

            viewModel.allNotes.observe(viewLifecycleOwner){list ->
                list?.let {
                    adapter.updateList(list)
                }
            }
            database = NotesDataBase.getDataBase(requireContext())
        }

        private fun initUI(){
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
            adapter = NotesAdaptor(requireContext(),this)
            binding.recyclerView.adapter = adapter

            val getContent =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
                    if(result.resultCode == Activity.RESULT_OK){
                        val note = result.data?.getSerializableExtra("note") as? EntityDataBase
                        if (note != null) {
                            viewModel.insertNote(note)
                        }
                    }
                }

            binding.addNote.setOnClickListener {
                val intent = Intent(requireContext(), add_notes::class.java)
                getContent.launch(intent)
            }
        }

        private val UpdateOrDeleteNote =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
                if(result.resultCode == Activity.RESULT_OK){
                    val note = result.data?.getSerializableExtra("note") as EntityDataBase
                    val isDelete = result.data?.getBooleanExtra("delete_note", false) as Boolean
                    if(note != null && !isDelete){
                        viewModel.updateNote(note)
                    }
                    else if(note != null && isDelete){
                        viewModel.deleteNote(note)
                    }
                }
            }
        override fun onNoteClicked(note: EntityDataBase){
            val intent = Intent(requireContext(),add_notes::class.java)
            intent.putExtra("current_note", note)
            UpdateOrDeleteNote.launch(intent)
        }
    }