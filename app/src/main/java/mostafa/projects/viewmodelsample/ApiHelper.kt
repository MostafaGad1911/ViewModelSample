package mostafa.projects.viewmodelsample

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiHelper {
    companion object {
        fun getMyApis(url: String): ApiInterfaces {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apis: ApiInterfaces = retrofit.create(ApiInterfaces::class.java)
            return apis
        }

    }
}