package mostafa.projects.viewmodelsample

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class myViewModel : ViewModel() {


    var data: MutableLiveData<Int> = MutableLiveData()
    var invalidNum: MutableLiveData<Int> = MutableLiveData()
    var invalidZero: MutableLiveData<Int> = MutableLiveData()


    var error_message: MutableLiveData<String> = MutableLiveData()
    var productDataList: MutableLiveData<ArrayList<Data>> = MutableLiveData()

    fun getProduct():LiveData<ArrayList<Data>>{
        return productDataList
    }

    fun getProducts() {
        ApiHelper.getMyApis("https://android-training.appssquare.com/api/")
            .getData()
            .enqueue(object : Callback<ProductsData> {
                override fun onResponse(
                    call: Call<ProductsData>,
                    response: Response<ProductsData>
                ) {
                    when (response.code()) {
                        200 -> {
                            var data: ArrayList<Data> = response.body()?.data!!
                            productDataList.postValue(data)
                        }

                        else -> {
                            val errorJsonString = response?.errorBody()?.string()
                            val json: JSONObject = JSONObject(errorJsonString)
                            val msg = json.getString("message")
                            error_message.postValue(msg)
                        }
                    }

                }

                override fun onFailure(call: Call<ProductsData>, t: Throwable) {
                    error_message.postValue(t.message)
                }
            })
    }
}