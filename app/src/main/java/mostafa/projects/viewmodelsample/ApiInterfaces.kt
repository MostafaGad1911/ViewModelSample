package mostafa.projects.viewmodelsample

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfaces {

    @GET("products/")
    fun getData(): Call<ProductsData>
}