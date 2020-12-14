package com.dmgdavid2109.challenge.feature.ui.balance

import androidx.fragment.app.Fragment
import com.dmgdavid2109.challenge.di.FragmentKey
import com.dmgdavid2109.challenge.feature.ui.tokens.TokensFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class BalanceFragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(BalanceFragment::class)
    abstract fun bindListFragment(mainFragment: BalanceFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(TokensFragment::class)
    abstract fun bindDetailsFragment(mainFragment: TokensFragment): Fragment

}
