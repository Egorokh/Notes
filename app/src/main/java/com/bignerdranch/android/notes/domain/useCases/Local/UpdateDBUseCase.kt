package com.bignerdranch.android.notes.domain.useCases.Local

import com.bignerdranch.android.notes.domain.NotesRepository
import com.bignerdranch.android.notes.domain.models.NotesDomain

class UpdateDBUseCase(private val repository: NotesRepository) {
    suspend fun executeUpdate(notesDomain: NotesDomain){
        repository.update(notesDomain)
    }
}