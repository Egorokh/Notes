package com.bignerdranch.android.notes.presentation.addNotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.notes.data.databaseFirebase.NoteFirebase
import com.bignerdranch.android.notes.domain.models.NotesDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNotesViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val _note = MutableLiveData<NotesDomain?>()
    val note: LiveData<NotesDomain?> = _note

    private val _noteFirebase = MutableLiveData<NoteFirebase?>()
    val noteFirebase: LiveData<NoteFirebase?> = _noteFirebase

    fun setNoteData(note: NotesDomain?){
        _note.value = note
    }

    fun setNoteFirebaseData(noteFirebase: NoteFirebase?){
        _noteFirebase.value = noteFirebase
    }
}