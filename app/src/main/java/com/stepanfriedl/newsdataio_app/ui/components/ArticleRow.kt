package com.stepanfriedl.newsdataio_app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stepanfriedl.newsdataio_app.R
import com.stepanfriedl.newsdataio_app.data.model.Article
import com.stepanfriedl.newsdataio_app.ui.theme.LightGray
import com.stepanfriedl.newsdataio_app.utils.DateUtils

@Composable
fun ArticleRow(
    article: Article,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth()
                .background(
                    LightGray.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(15f)
            ) {
                Text(
                    article.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(DateUtils.formatDate(article.pubDate))
            }

            Image(
                painter = painterResource(id = R.drawable.chevron_right),
                contentDescription = "Chevron right",
                modifier = Modifier
                    .size(24.dp)
                    .weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleRowPreview() {
    val exampleArticle = Article(
        article_id = "f2f0186ce3ab83cdb6681b2d313f9083",
        title = "El Concello define la remodelación del área marítima de Portosín para integrar ocioEl Concello define la remodelación del área marítima de Portosín para integrar ocio y actividad pesquera",
        link = "https://www.lavozdegalicia.es/noticia/barbanza/2024/11/13/concello-define-remodelacion-area-maritima-portosin-integrar-ocio-actividad-pesquera/0003_202411B13C6993.htm",
        keywords = listOf("consellería do mar", "barbanza", "porto do son", "marítima"),
        creator = listOf("A. Gerpe"),
        video_url = null,
        description = "El regidor, Luis Oujo, mantuvo contactos con los arquitectos de la galardonada obra de la villa de O Son para estudiar la zona",
        content = "ONLY AVAILABLE IN PAID PLANS",
        pubDate = "2024-11-13 03:50:00",
        pubDateTZ = "UTC",
        image_url = "https://cflvdg.avoz.es/sc/q9DlcgRL7pNLRGQNmEuF1QwRMmQ=/768x/2024/11/12/00121731417962080955760/Foto/B22J1112.jpg",
        source_id = "lavozdegalicia",
        source_priority = 65618,
        source_name = "La Voz De Galicia",
        source_url = "https://www.lavozdegalicia.es",
        source_icon = "https://i.bytvi.com/domain_icons/lavozdegalicia.png",
        language = "spanish",
        country = listOf("spain"),
        category = listOf("other"),
        ai_tag = "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLANS",
        sentiment = "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLANS",
        sentiment_stats = "ONLY AVAILABLE IN PROFESSIONAL AND CORPORATE PLANS",
        ai_region = "ONLY AVAILABLE IN CORPORATE PLANS",
        ai_org = "ONLY AVAILABLE IN CORPORATE PLANS",
        duplicate = false
    )

    ArticleRow(
        article = exampleArticle,
        onClick = {}
    )
}