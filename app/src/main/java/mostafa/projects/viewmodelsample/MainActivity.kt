package mostafa.projects.viewmodelsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    val myViewModel: myViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObjects()

//        myViewModel = ViewModelProvider(this).get(myViewModel::class.java)

        myViewModel.getProducts()
        myViewModel.getProduct().observe(this, Observer {
             Log.i("PRODUCTS" , "${it.get(0).name}")
        })

        myViewModel.error_message.observe(this , Observer {
            Toast.makeText(this , it , Toast.LENGTH_SHORT).show()
        })

    }

    fun initObjects() {

    }

    override fun onStop() {
        myViewModel.invalidZero.removeObservers(this)
        super.onStop()
    }
}