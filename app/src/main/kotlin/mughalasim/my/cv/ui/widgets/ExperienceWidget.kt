package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import cv.data.extensions.toMonthYearDuration
import cv.data.extensions.toMonthYearString
import cv.domain.entities.ExperienceEntity
import mughalasim.my.cv.databinding.WidgetExperienceBinding
import mughalasim.my.cv.ui.utils.LinkObject
import org.joda.time.DateTime

class ExperienceWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding: WidgetExperienceBinding =
        WidgetExperienceBinding.inflate(LayoutInflater.from(context), this, true)

    fun setUp(model: ExperienceEntity) {

        binding.txtTitle.text = model.title
        binding.txtSubtitle.text = model.position_title
        binding.txtDescription.text = model.description

        val dateLocationString = if (!model.ongoing)
            "${model.getStartDate().toMonthYearString()} - ${model.getEndDate().toMonthYearString()}, ${model.location}"
        else
            "${model.getStartDate().toMonthYearString()} - Present, ${model.location}"

        binding.txtDateLocation.text = dateLocationString
        val timeSpentString = if (model.ongoing)
            DateTime.now().toMonthYearDuration(context, model.getStartDate())
        else
            model.getEndDate().toMonthYearDuration(context, model.getStartDate())

        binding.txtTimeSpent.visibility = if(timeSpentString.isEmpty()) View.GONE else View.VISIBLE
        binding.txtTimeSpent.text = timeSpentString

        LinkObject.setUp(context, model.links, binding.labelLinks, binding.chipGroup)

    }

}