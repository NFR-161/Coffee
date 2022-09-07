package com.exampleone.test.data.dataSource

import android.content.Context
import android.widget.ProgressBar

interface CoffeeApiDataSource {
    fun startMigration (context: Context,progressBar_ID: ProgressBar)
}