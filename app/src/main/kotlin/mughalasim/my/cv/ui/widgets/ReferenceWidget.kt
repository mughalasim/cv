package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mughalasim.my.cv.ui.theme.AppTheme

//class ReferenceWidget @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {
//
//    private val binding: WidgetReferenceBinding =
//        WidgetReferenceBinding.inflate(LayoutInflater.from(context), this, true)
//
//    fun setUp(model: ReferenceEntity) {
//        binding.txtFullName.text = model.name
//        binding.txtContact.text = model.contact
//        binding.txtCompany.text = model.company
//    }
//
//}

@Composable
fun ReferenceWidget(title: String){
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
fun PreviewReferenceWidget(){
    ReferenceWidget("Text title")
}