package me.dio.copa.catar.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.dio.copa.catar.notification.scheduler.extensions.NotificationMatchWorker
import me.dio.copa.catar.ui.theme.Copa2022Theme
import me.dio.copa.catar.utils.observe

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeActions()
        setContent {
            Copa2022Theme {
                MainScreen()
            }
        }
    }

    private fun observeActions() {
        viewModel.action.observe(this) { action ->
            when (action) {
                is MainUiAction.MatchesNotFound -> {}
                MainUiAction.Unexpected -> {}
                is MainUiAction.DisableNotification ->
                    NotificationMatchWorker.cancel(applicationContext, action.match)
                is MainUiAction.EnableNotification ->
                    NotificationMatchWorker.start(applicationContext, action.match)
            }
        }
    }
}
