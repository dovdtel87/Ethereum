package com.dmgdavid2109.challenge.feature.ui.balance

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.dmgdavid2109.challenge.common.ui.viewBinding
import com.dmgdavid2109.challenge.R
import com.dmgdavid2109.challenge.common.ui.EventObserver
import com.dmgdavid2109.challenge.common.ui.setViewModelInputs
import com.dmgdavid2109.challenge.common.ui.setViewState
import com.dmgdavid2109.challenge.databinding.BalanceFragmentBinding
import com.dmgdavid2109.challenge.di.ViewModelFactory
import javax.inject.Inject

class BalanceFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory<BalanceViewModel>
) : Fragment((R.layout.balance_fragment)) {
    private val binding by viewBinding(BalanceFragmentBinding::bind)

    private val balanceViewModel: BalanceViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
    }

    private fun bindView() {

        binding.button.setOnClickListener {
            balanceViewModel.onButtonTapped()
        }

        binding.loadingView.setViewModelInputs(viewModelInputs = balanceViewModel)

        balanceViewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            binding.loadingView.setViewState(viewState)
            binding.addressValue.text = viewState.address
            binding.ethValue.text = viewState.balance
        })

        balanceViewModel.navigateToTokens.observe(viewLifecycleOwner, EventObserver {
            navigateToTokensScreen()
        })
    }

    private fun navigateToTokensScreen() {
        val action =
            BalanceFragmentDirections.actionBalanceFragmentToTokensFragment()
            findNavController().navigate(action)
    }
}
