package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import mughalasim.my.cv.data.models.ReferenceModel
import mughalasim.my.cv.databinding.WidgetReferenceBinding

class ReferenceWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    internal val binding: WidgetReferenceBinding =
        WidgetReferenceBinding.inflate(LayoutInflater.from(context), this, true)

    fun setUp(model: ReferenceModel) {
        binding.txtFullName.text = model.name
        binding.txtContact.text = model.contact
        binding.txtCompany.text = model.company
    }

}