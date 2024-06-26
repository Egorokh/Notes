package com.bignerdranch.android.notes.domain

import androidx.lifecycle.LiveData
import com.bignerdranch.android.notes.data.databaseFirebase.NoteFirebase

interface FirebaseNotesRepository {

    val firebaseNotes: LiveData<List<NoteFirebase>>
    val getAllArchivedNotesFirebase: LiveData<List<NoteFirebase>>
    val getAllDeleteNotesFirebase: LiveData<List<NoteFirebase>>

    suspend fun insertFirebaseNote(note: NoteFirebase)
    suspend fun updateFirebaseNote(note: NoteFirebase)
    suspend fun deleteFirebaseNote(note: NoteFirebase)
}