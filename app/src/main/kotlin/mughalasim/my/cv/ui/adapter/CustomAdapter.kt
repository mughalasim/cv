package mughalasim.my.cv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.widgets.BannerWidget
import mughalasim.my.cv.ui.widgets.DescriptionWidget
import mughalasim.my.cv.ui.widgets.SkillWidget

class CustomAdapter(private val dataSet: List<RecyclerData>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: DescriptionWidget = view.findViewById(R.id.widget_description)
        val banner: BannerWidget = view.findViewById(R.id.widget_banner)
        val skill: SkillWidget = view.findViewById(R.id.widget_skill)

    }

    override fun onCreateViewHolder(vg: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(vg.context)
            .inflate(R.layout.list_item_recycler, vg, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        vh.description.visibility = View.GONE
        vh.banner.visibility = View.GONE
        vh.skill.visibility = View.GONE


        when(dataSet[position].type){
            AdapterType.TITLE -> {
                vh.banner.setUp(dataSet[position].title!!)
                vh.banner.visibility = View.VISIBLE
            }

            AdapterType.DESCRIPTION -> {
                vh.description.setUp(dataSet[position].description!!)
                vh.description.visibility = View.VISIBLE
            }

            AdapterType.SKILL -> {
                vh.skill.setUp(dataSet[position].skill!!)
                vh.skill.visibility = View.VISIBLE
            }

            AdapterType.WORK -> {}

            AdapterType.EDUCATION -> {}

            AdapterType.REFERENCE -> {}
        }
    }

    override fun getItemCount() = dataSet.size

}