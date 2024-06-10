package com.bignerdranch.android.notes.domain.useCases.Local

import com.bignerdranch.android.notes.domain.NotesRepository
import com.bignerdranch.android.notes.domain.models.NotesDomain

class InsertToDBUseCase(private val repository: NotesRepository) {
    suspend fun executeInsert(notesDomain: NotesDomain){
        repository.insert(notesDomain)
    }
}