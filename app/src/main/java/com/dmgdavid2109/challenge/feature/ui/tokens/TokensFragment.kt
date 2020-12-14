package com.dmgdavid2109.challenge.feature.ui.tokens

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dmgdavid2109.challenge.R
import com.dmgdavid2109.challenge.common.ui.setToolbar
import com.dmgdavid2109.challenge.common.ui.setViewModelInputs
import com.dmgdavid2109.challenge.common.ui.setViewState
import com.dmgdavid2109.challenge.common.ui.viewBinding
import com.dmgdavid2109.challenge.databinding.TokensFragmentBinding
import com.dmgdavid2109.challenge.di.ViewModelFactory
import com.dmgdavid2109.challenge.feature.domain.model.Token
import java.math.BigDecimal
import javax.inject.Inject

class TokensFragment @Inject constructor(
    private val viewModelFactory: ViewModelFactory<TokensViewModel>
) : Fragment(R.layout.tokens_fragment) {

    private val binding by viewBinding(TokensFragmentBinding::bind)

    private val viewModel: TokensViewModel by viewModels {
        viewModelFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        bindView()
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity).setToolbar(binding.toolbar, getString(R.string.erc_20_tokens), true)
    }

    private fun bindView() {
        binding.loadingView.setViewModelInputs(viewModel)

        viewModel.viewState.observe(viewLifecycleOwner, Observer { viewState ->
            with(viewState.tokens) {
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, this)
                binding.loadingView.setViewState(viewState)
                binding.autoComplete.setAdapter(adapter)
                binding.autoComplete.threshold = 1
                binding.autoComplete.setOnItemClickListener { _, _, position, _ ->
                    val value = adapter.getItem(position) ?: "" as Token
                    viewModel.getBalance(value.address)
                }
            }

            with(viewState.balance) {
                binding.balance.setTextColor(ContextCompat.getColor(requireContext(), if (this > BigDecimal.ZERO) {
                    R.color.green
                } else {
                    R.color.red
                }))
                binding.balance.text = this.toString()
            }
        })
    }
}
