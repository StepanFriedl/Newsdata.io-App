package com.stepanfriedl.newsdataio_app.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.stepanfriedl.newsdataio_app.data.model.Article
import com.stepanfriedl.newsdataio_app.data.repository.NewsRepository

class DetailsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    fun getArticle(id: String): Article? =
        newsRepository.getArticle(id = id)

    fun shareUrl(context: Context, url: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun openLink(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent)
    }
}