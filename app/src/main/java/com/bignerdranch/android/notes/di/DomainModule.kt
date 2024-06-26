package com.bignerdranch.android.notes.di

import com.bignerdranch.android.notes.domain.FirebaseNotesRepository
import com.bignerdranch.android.notes.domain.NotesRepository
import com.bignerdranch.android.notes.domain.useCases.Local.DeleteFromDBUseCase
import com.bignerdranch.android.notes.domain.useCases.Local.GetAllNotesListUseCase
import com.bignerdranch.android.notes.domain.useCases.Local.InsertToDBUseCase
import com.bignerdranch.android.notes.domain.useCases.Local.UpdateDBUseCase
import com.bignerdranch.android.notes.domain.useCases.Server.FirebaseDeleteFromDBUseCase
import com.bignerdranch.android.notes.domain.useCases.Server.FirebaseGetAllNotesListUseCase
import com.bignerdranch.android.notes.domain.useCases.Server.FirebaseInsertToDBUseCase
import com.bignerdranch.android.notes.domain.useCases.Server.FirebaseUpdateDBUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideDeleteFromDBUseCase(notesRepository: NotesRepository): DeleteFromDBUseCase {
        return DeleteFromDBUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllNotesUseCase(notesRepository: NotesRepository): GetAllNotesListUseCase {
        return GetAllNotesListUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideInsertToDBUseCase(notesRepository: NotesRepository): InsertToDBUseCase {
        return InsertToDBUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateDBUseCase(notesRepository: NotesRepository): UpdateDBUseCase {
        return UpdateDBUseCase(notesRepository)
    }

    @Singleton
    @Provides
    fun provideFirebaseDeleteFromDBUseCase(firebaseNotesRepository: FirebaseNotesRepository): FirebaseDeleteFromDBUseCase {
        return FirebaseDeleteFromDBUseCase(firebaseNotesRepository)
    }

    @Singleton
    @Provides
    fun provideFirebaseGetAllNotesUseCase(firebaseNotesRepository: FirebaseNotesRepository): FirebaseGetAllNotesListUseCase {
        return FirebaseGetAllNotesListUseCase(firebaseNotesRepository)
    }

    @Singleton
    @Provides
    fun provideFirebaseInsertToDBUseCase(firebaseNotesRepository: FirebaseNotesRepository): FirebaseInsertToDBUseCase {
        return FirebaseInsertToDBUseCase(firebaseNotesRepository)
    }

    @Singleton
    @Provides
    fun provideFirebaseUpdateDBUseCase(firebaseNotesRepository: FirebaseNotesRepository): FirebaseUpdateDBUseCase {
        return FirebaseUpdateDBUseCase(firebaseNotesRepository)
    }


}