package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import mughalasim.my.cv.R
import mughalasim.my.cv.data.models.LinksModel
import mughalasim.my.cv.ui.DateTimeExtensions.dateFormatDayFullMonthYear
import org.joda.time.*
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object LinkObject {

    fun setUp (context: Context, links: List<LinksModel>, txt: TextView, chipGroup: ChipGroup){
        txt.visibility = if(links.isEmpty()) View.GONE else View.VISIBLE

        for (link in links){
            val chip = Chip(context)
            chip.text = link.text
            chip.setOnClickListener {
                if (!link.url.startsWith("http://") || !link.url.startsWith("https://")) return@setOnClickListener
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link.url))
                context.startActivity(browserIntent)
            }
            chipGroup.addView(chip)
        }
    }
}
