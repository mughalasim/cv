package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mughalasim.my.cv.ui.theme.AppTheme

//class DescriptionWidget @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {
//
//    private val binding: WidgetDescriptionBinding =
//        WidgetDescriptionBinding.inflate(LayoutInflater.from(context), this, true)
//
//    fun setUp(model: DescriptionEntity) {
//        binding.banner.setUp("Contact information")
//        binding.txtFullName.text = model.full_name
//        binding.txtAddress.text = model.address
//        binding.txtEmail.text = model.email
//        binding.txtPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
//        val fullNumber = "+${model.area_code}${model.phone}"
//        binding.txtPhone.text = fullNumber
//        binding.txtPositionTitle.text = model.position_title
//
//        LinkObject.setUp(context, model.links, binding.labelLinks, binding.chipGroup)
//
//    }
//
//}


@Composable
fun DescriptionWidget(title: String){
    TextRegular(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp, start = 0.dp, end = 0.dp)
            .background(AppTheme.colors.secondary)
    )
}

@Preview
@Composable
fun PreviewDescriptionWidget(){
    DescriptionWidget("Text title")
}