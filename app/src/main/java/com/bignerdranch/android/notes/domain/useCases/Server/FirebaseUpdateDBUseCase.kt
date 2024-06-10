package com.bignerdranch.android.notes.domain.useCases.Server

import com.bignerdranch.android.notes.data.databaseFirebase.NoteFirebase
import com.bignerdranch.android.notes.domain.FirebaseNotesRepository

class FirebaseUpdateDBUseCase(private val repository: FirebaseNotesRepository) {
    suspend fun firebaseExecuteUpdate(noteFirebase: NoteFirebase){
        return repository.updateFirebaseNote(noteFirebase)
    }
}