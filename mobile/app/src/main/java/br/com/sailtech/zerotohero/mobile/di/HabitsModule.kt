package br.com.sailtech.zerotohero.mobile.di

import br.com.sailtech.zerotohero.mobile.data.HabitsRepositoryImpl
import br.com.sailtech.zerotohero.mobile.domain.repository.HabitsRepository
import br.com.sailtech.zerotohero.mobile.domain.usecase.GetHabitsUseCase
import br.com.sailtech.zerotohero.mobile.domain.usecase.GetHabitsUseCaseImpl
import br.com.sailtech.zerotohero.mobile.presentation.viewmodel.HabitsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
    viewModel { HabitsViewModel(getHabitsUseCase = get()) }
}

val habitsModule = listOf(
    domainModule,
    dataModule,
    presentationModule,
)
