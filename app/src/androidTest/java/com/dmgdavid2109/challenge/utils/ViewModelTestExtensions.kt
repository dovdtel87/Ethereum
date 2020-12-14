package com.dmgdavid2109.challenge.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmgdavid2109.challenge.di.ViewModelFactory
import dagger.internal.InstanceFactory

fun <T : ViewModel> T.toFactory(): ViewModelFactory<T> {
    return ViewModelFactory<T>(InstanceFactory.create(this) as InstanceFactory)
}

fun <T> T.toLiveData(): LiveData<T> {
    return MutableLiveData<T>().apply {
        value = this@toLiveData
    }
}
