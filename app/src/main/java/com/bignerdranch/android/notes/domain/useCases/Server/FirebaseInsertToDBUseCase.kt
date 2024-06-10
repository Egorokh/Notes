package com.bignerdranch.android.notes.domain.useCases.Server

import com.bignerdranch.android.notes.data.databaseFirebase.NoteFirebase
import com.bignerdranch.android.notes.domain.FirebaseNotesRepository

class FirebaseInsertToDBUseCase(private val repository: FirebaseNotesRepository) {
    suspend fun firebaseExecuteInsert(noteFirebase: NoteFirebase){
        repository.insertFirebaseNote(noteFirebase)
    }
}