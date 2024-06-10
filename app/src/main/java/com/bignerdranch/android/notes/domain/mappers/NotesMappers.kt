package com.bignerdranch.android.notes.domain.mappers

import com.bignerdranch.android.notes.data.database.EntityDataBase
import com.bignerdranch.android.notes.domain.models.NotesDomain

fun EntityDataBase.toDomainModel(): NotesDomain {
    return NotesDomain(id, title, note, date, isArchived, isDelete)
}

fun NotesDomain.toEntity(): EntityDataBase {
    return EntityDataBase(id, title, note, date, isArchived, isDelete)
}