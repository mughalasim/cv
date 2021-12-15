package mughalasim.my.cv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mughalasim.my.cv.R
import mughalasim.my.cv.ui.widgets.*

class CustomAdapter(private val dataSet: List<RecyclerData>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: DescriptionWidget = view.findViewById(R.id.widget_description)
        val banner: BannerWidget = view.findViewById(R.id.widget_banner)
        val skill: SkillWidget = view.findViewById(R.id.widget_skill)
        val experience: ExperienceWidget = view.findViewById(R.id.widget_experience)
        val reference: ReferenceWidget = view.findViewById(R.id.widget_reference)
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
        vh.experience.visibility = View.GONE
        vh.reference.visibility = View.GONE

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

            AdapterType.WORK -> {
                vh.experience.setUp(dataSet[position].work!!)
                vh.experience.visibility = View.VISIBLE
            }

            AdapterType.EDUCATION -> {
                vh.experience.setUp(dataSet[position].education!!)
                vh.experience.visibility = View.VISIBLE
            }

            AdapterType.REFERENCE -> {
                vh.reference.setUp(dataSet[position].reference!!)
                vh.reference.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount() = dataSet.size

}