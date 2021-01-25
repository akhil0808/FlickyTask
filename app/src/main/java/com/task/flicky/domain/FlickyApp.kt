package com.task.flicky.domain

import android.app.Application
import com.task.flicky.domain.database.FlickyDatabase
import com.task.flicky.domain.network.ServiceGenerator
import com.task.flicky.domain.dataSource.FlickyDataSource
import com.task.flicky.domain.dataSource.FlickyDataSourceImp
import com.task.flicky.domain.repository.FlickerRepository
import com.task.flicky.domain.repository.FlickyRepositoryImp
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton




class FlickyApp:Application(),KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FlickyApp))

        bind() from singleton { FlickyDatabase(instance()) }
        bind() from singleton { instance<FlickyDatabase>().feedDao() }
        bind() from singleton { ServiceGenerator() }
        bind<FlickyDataSource>() with singleton { FlickyDataSourceImp(instance()) }
        bind<FlickerRepository>() with singleton {
            FlickyRepositoryImp(instance(),instance())}
        bind() from provider { ViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()

    }
}