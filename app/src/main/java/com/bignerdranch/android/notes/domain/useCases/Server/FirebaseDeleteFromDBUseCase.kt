package com.bignerdranch.android.notes.domain.useCases.Server

import com.bignerdranch.android.notes.data.databaseFirebase.NoteFirebase
import com.bignerdranch.android.notes.domain.FirebaseNotesRepository

class FirebaseDeleteFromDBUseCase(private val repository: FirebaseNotesRepository) {
    suspend fun firebaseExecute(noteFirebase: NoteFirebase){
        repository.deleteFirebaseNote(noteFirebase)
    }
}