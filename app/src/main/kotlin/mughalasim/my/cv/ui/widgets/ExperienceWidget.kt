package mughalasim.my.cv.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mughalasim.my.cv.ui.theme.AppTheme

//class ExperienceWidget @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null,
//    defStyleAttr: Int = 0
//) : LinearLayout(context, attrs, defStyleAttr) {
//
//    private val binding: WidgetExperienceBinding =
//        WidgetExperienceBinding.inflate(LayoutInflater.from(context), this, true)
//
//    fun setUp(model: ExperienceEntity) {
//
//        binding.txtTitle.text = model.title
//        binding.txtSubtitle.text = model.position_title
//        binding.txtDescription.text = model.description
//
//        val dateLocationString = if (!model.ongoing)
//            "${model.getStartDate().toMonthYearString()} - ${model.getEndDate().toMonthYearString()}, ${model.location}"
//        else
//            "${model.getStartDate().toMonthYearString()} - Present, ${model.location}"
//
//        binding.txtDateLocation.text = dateLocationString
//        val timeSpentString = if (model.ongoing)
//            DateTime.now().toMonthYearDuration(context, model.getStartDate())
//        else
//            model.getEndDate().toMonthYearDuration(context, model.getStartDate())
//
//        binding.txtTimeSpent.visibility = if(timeSpentString.isEmpty()) View.GONE else View.VISIBLE
//        binding.txtTimeSpent.text = timeSpentString
//
//        LinkObject.setUp(context, model.links, binding.labelLinks, binding.chipGroup)
//
//    }
//
//}

@Composable
fun ExperienceWidget(title: String){
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
fun PreviewExperienceWidget(){
    ExperienceWidget("Text title")
}