package com.bignerdranch.android.notes.domain.useCases.Server

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notes.data.databaseFirebase.NoteFirebase
import com.bignerdranch.android.notes.domain.FirebaseNotesRepository

class FirebaseGetAllNotesListUseCase(private val repository: FirebaseNotesRepository) {
    fun firebaseExecuteNotes(): LiveData<List<NoteFirebase>> {
        return repository.firebaseNotes
    }

    fun firebaseExecuteArchived(): LiveData<List<NoteFirebase>>{
        return repository.getAllArchivedNotesFirebase
    }

    fun firebaseExecuteDelete(): LiveData<List<NoteFirebase>>{
        return repository.getAllDeleteNotesFirebase
    }
}