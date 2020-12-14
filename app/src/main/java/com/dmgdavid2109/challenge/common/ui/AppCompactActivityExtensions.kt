package com.dmgdavid2109.challenge.common.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun AppCompatActivity.setToolbar(toolbar: Toolbar, title: String? = null, showHomeButton: Boolean = false) {
    setSupportActionBar(toolbar)

    supportActionBar?.setDisplayHomeAsUpEnabled(showHomeButton)

    title?.let { unwrappedTitle ->
        supportActionBar?.title = unwrappedTitle
        supportActionBar?.setDisplayShowTitleEnabled(true)
    } ?: run {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    toolbar.setNavigationOnClickListener {
        onBackPressed()
    }
}
