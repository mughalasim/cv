package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import mughalasim.my.cv.data.models.SkillModel
import mughalasim.my.cv.databinding.WidgetSkillBinding

class SkillWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    internal val binding: WidgetSkillBinding =
        WidgetSkillBinding.inflate(LayoutInflater.from(context), this, true)

    fun setUp(model: SkillModel) {
        binding.txtSkill.text = model.title
        binding.txtDescription.text = model.description
    }

}