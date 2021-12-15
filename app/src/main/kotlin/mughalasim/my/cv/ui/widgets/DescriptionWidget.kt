package mughalasim.my.cv.ui.widgets

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.google.android.material.chip.Chip
import mughalasim.my.cv.data.models.DescriptionModel
import mughalasim.my.cv.databinding.WidgetDescriptionBinding

class DescriptionWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    internal val binding: WidgetDescriptionBinding =
        WidgetDescriptionBinding.inflate(LayoutInflater.from(context), this, true)

    fun setUp(model: DescriptionModel) {
        binding.banner.setUp("Contact information")
        binding.txtFullName.text = model.full_name
        binding.txtAddress.text = model.address
        binding.txtEmail.text = model.email
        binding.txtPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        val fullNumber = "+${model.area_code}${model.phone}"
        binding.txtPhone.text = fullNumber
        binding.txtPositionTitle.text = model.position_title

        LinkObject.setUp(context, model.links, binding.labelLinks, binding.chipGroup)

    }

}