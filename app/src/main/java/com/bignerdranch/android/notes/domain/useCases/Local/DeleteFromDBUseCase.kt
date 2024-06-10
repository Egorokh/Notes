package com.bignerdranch.android.notes.domain.useCases.Local

import com.bignerdranch.android.notes.domain.NotesRepository
import com.bignerdranch.android.notes.domain.models.NotesDomain

class DeleteFromDBUseCase(private val repository: NotesRepository) {
    suspend fun execute(notesDomain: NotesDomain){
        repository.delete(notesDomain)
    }
}