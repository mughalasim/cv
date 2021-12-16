package mughalasim.my.cv.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import mughalasim.my.cv.databinding.ActivityMainBinding
import mughalasim.my.cv.ui.adapter.CustomAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: GeneralViewModel by viewModel()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getRecyclerData()

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            viewModel.getRecyclerData()
        }

        viewModel.data.observe(this, {
            binding.recycler.adapter = CustomAdapter(it)
        })

        viewModel.showMessage.observe(this, {
            binding.txtMessage.visibility = if (it) View.VISIBLE else View.GONE
        })

    }
}