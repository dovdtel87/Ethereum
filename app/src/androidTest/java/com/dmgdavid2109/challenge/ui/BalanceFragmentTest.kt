package com.dmgdavid2109.challenge.ui

import com.dmgdavid2109.challenge.R
import com.dmgdavid2109.challenge.utils.createFactoryWithNavController
import com.dmgdavid2109.challenge.utils.toFactory
import com.dmgdavid2109.challenge.utils.toLiveData
import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dmgdavid2109.challenge.feature.ui.balance.BalanceFragment
import com.dmgdavid2109.challenge.feature.ui.balance.BalanceViewModel
import com.dmgdavid2109.challenge.feature.ui.balance.BalanceViewState
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoriesListFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var fragmentScenario: FragmentScenario<BalanceFragment>

    @Test
    fun itDisplaysRepositoriesList() {
        // Given
        val balanceViewState = BalanceViewState(
            isLoading = false,
            errorMessage = null,
            address = "0x082d3e0f04664b65127876e9A05e2183451c792a",
            balance = "0.9 ETH"
        )
        val viewModel = mockk<BalanceViewModel>(relaxed = true) {
            every { viewState } returns balanceViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        onView(withId(R.id.addressValue)).check(ViewAssertions.matches(ViewMatchers.withText("0x082d3e0f04664b65127876e9A05e2183451c792a")))
        onView(withId(R.id.ethValue)).check(ViewAssertions.matches(ViewMatchers.withText("0.9 ETH")))
    }

    @Test
    fun itDisplaysLoadingView() {
        // Given
        val balanceViewState = BalanceViewState(
            isLoading = true,
            errorMessage = null,
            address = "",
            balance = ""
        )
        val viewModel: BalanceViewModel = mockk(relaxed = true) {
            every { viewState } returns balanceViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun itDisplaysErrorView() {
        // Given
        val balanceViewState = BalanceViewState(
            isLoading = false,
            errorMessage = R.string.generic_error,
            address = "",
            balance = ""
        )
        val viewModel: BalanceViewModel = mockk(relaxed = true) {
            every { viewState } returns balanceViewState.toLiveData()
        }

        // When
        startFragment(viewModel)

        // Then
        onView(withId(R.id.error_description)).check(ViewAssertions.matches(ViewMatchers.withText(R.string.generic_error)))
    }

    private fun startFragment(viewModel: BalanceViewModel) {
        fragmentScenario = launchFragmentInContainer(
            Bundle(),
            themeResId = R.style.AppTheme,
            factory = createFactoryWithNavController {
                BalanceFragment(viewModel.toFactory())
            }
        )
    }
}
