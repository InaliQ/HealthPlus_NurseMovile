
import com.example.health_plus_nurse.apis.AuthApiService
import com.example.health_plus_nurse.apis.DashBoardApiService
import com.example.health_plus_nurse.apis.PacienteApiService
import com.example.health_plus_nurse.apis.RecordatorioApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

object RetrofitClient {

    //private const val BASE_URL = "https://192.168.1.70:7283/api/"
    //private const val BASE_URL = "https://192.168.137.247:7283/api/"
    private const val BASE_URL = "https://healthplus-d5f0a3dqg6fwfxbq.mexicocentral-01.azurewebsites.net/api/ "
    private val unsafeOkHttpClient: OkHttpClient
        get() {
            return try {
                // Crea un TrustManager que confía en todos los certificados
                val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                })

                // Instala el TrustManager que confía en todos los certificados
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())

                // Crea un SSLSocketFactory con nuestro TrustManager
                val sslSocketFactory = sslContext.socketFactory

                OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                    .hostnameVerifier { _, _ -> true }  // Omite verificación del nombre del host
                    .build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }

    val instance: AuthApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(unsafeOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(AuthApiService::class.java)
    }

    val instancePacientes: PacienteApiService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(unsafeOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(PacienteApiService::class.java)
    }

    val instanceDasboard: DashBoardApiService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(unsafeOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(DashBoardApiService::class.java)
    }

    val instanceRecodatorio: RecordatorioApiService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(unsafeOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(RecordatorioApiService::class.java)
    }
}


