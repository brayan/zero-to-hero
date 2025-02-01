package br.com.sailtech.zerotohero.mobile.feature.habits.di

import br.com.sailtech.zerotohero.mobile.feature.habits.data.HabitsRepositoryImpl
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.repository.HabitsRepository
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.usecase.GetHabitsUseCase
import br.com.sailtech.zerotohero.mobile.feature.habits.domain.usecase.GetHabitsUseCaseImpl
import br.com.sailtech.zerotohero.mobile.feature.habits.presentation.viewmodel.HabitsViewModel
import org.koin.dsl.module

private val domainModule = module {
    factory<GetHabitsUseCase> {
        GetHabitsUseCaseImpl(habitsRepository = get())
    }
}

private val dataModule = module {
    factory<HabitsRepository> {
        HabitsRepositoryImpl()
    }
}

private val presentationModule = module {
    factory { HabitsViewModel(getHabitsUseCase = get()) }
}

val commonHabitsModule = listOf(
    domainModule,
    dataModule,
    presentationModule,
)
