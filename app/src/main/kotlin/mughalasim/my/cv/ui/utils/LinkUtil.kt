package mughalasim.my.cv.ui.utils

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.widget.Toast
import cv.domain.entities.LinkEntity
import org.koin.mp.KoinPlatformTools

fun openLink(linkEntity: LinkEntity){
    val context = KoinPlatformTools.defaultContext().get().get<IAppContext>().fetchAppContext()
    val url = linkEntity.url
    if (url.startsWith("http://") || !url.startsWith("https://")) {
        Toast.makeText(context, "Invalid Link Url", Toast.LENGTH_LONG).show()
        return
    }
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    browserIntent.flags = FLAG_ACTIVITY_NEW_TASK
    context.startActivity(browserIntent)
}
