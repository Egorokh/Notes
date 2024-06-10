package com.bignerdranch.android.notes.domain.useCases.Local

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notes.domain.NotesRepository
import com.bignerdranch.android.notes.domain.models.NotesDomain

class GetAllNotesListUseCase(private val repository: NotesRepository) {
    fun executeNotes(): LiveData<List<NotesDomain>> {
        return repository.allNotes
    }

    fun executeArchived(): LiveData<List<NotesDomain>>{
        return repository.getAllArchivedNotes
    }

    fun executeDelete(): LiveData<List<NotesDomain>>{
        return repository.getAllDeleteNotes
    }
}