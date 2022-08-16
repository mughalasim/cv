package mughalasim.my.cv.ui.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import cv.domain.entities.LinksEntity

object LinkObject {

    fun setUp (context: Context, links: List<LinksEntity>, txt: TextView, chipGroup: ChipGroup){
        chipGroup.removeAllViews()
        txt.visibility = if(links.isEmpty()) View.GONE else View.VISIBLE

        for (link in links){
            val chip = Chip(context)
            chip.text = link.text
            chip.contentDescription = link.url
            chip.setOnClickListener {
                val url = (it as Chip).contentDescription.toString()
                if (url.startsWith("http://") || !url.startsWith("https://")) return@setOnClickListener
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(browserIntent)
            }
            chipGroup.addView(chip)
        }
    }
}
