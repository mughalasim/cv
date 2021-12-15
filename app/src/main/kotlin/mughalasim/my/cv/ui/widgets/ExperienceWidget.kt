package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.google.android.material.chip.Chip
import mughalasim.my.cv.data.models.DescriptionModel
import mughalasim.my.cv.data.models.ExperienceModel
import mughalasim.my.cv.databinding.WidgetDescriptionBinding
import mughalasim.my.cv.databinding.WidgetExperienceBinding

class ExperienceWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    internal val binding: WidgetExperienceBinding =
        WidgetExperienceBinding.inflate(LayoutInflater.from(context), this, true)

    fun setUp(model: ExperienceModel) {

        binding.txtTitle.text = model.title
        binding.txtSubtitle.text = model.position_title
        binding.txtDescription.text = model.description

        binding.txtDateLocation.text = "TODO"
        binding.txtTimeSpent.text = "TODO"

        for (link in model.links){
            val chip = Chip(context)
            chip.text = link.text
            binding.chipGroup.addView(chip)
        }
    }

}