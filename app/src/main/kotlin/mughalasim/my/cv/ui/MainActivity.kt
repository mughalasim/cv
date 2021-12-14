package mughalasim.my.cv.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import mughalasim.my.cv.data.models.DescriptionModel
import mughalasim.my.cv.data.models.ResponseModel
import mughalasim.my.cv.databinding.ActivityMainBinding
import mughalasim.my.cv.ui.adapter.AdapterType
import mughalasim.my.cv.ui.adapter.CustomAdapter
import mughalasim.my.cv.ui.adapter.RecyclerData

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseDatabase.getInstance().reference.get().addOnSuccessListener {

            val response = it.getValue(ResponseModel::class.java)

            val recyclerData: MutableList<RecyclerData> = mutableListOf()
            recyclerData.add(RecyclerData(description = response!!.description, type = AdapterType.DESCRIPTION))

            if (response.skills.size > 0){
                recyclerData.add(RecyclerData(title = "Skills", type = AdapterType.TITLE))
                for (skill in response.skills){
                    recyclerData.add(RecyclerData(skill = skill, type = AdapterType.SKILL))
                }

            }

            binding.recycler.adapter = CustomAdapter(recyclerData)

        }.addOnFailureListener {
            println(it.localizedMessage)
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false

        }

    }
}