
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

object RetrofitClient {

    private const val BASE_URL = "https://192.168.1.70:7283/api/"
    //private const val BASE_URL = "https://192.168.137.247:7283/api/"
    //private const val BASE_URL = "https://healthplus-d5f0a3dqg6fwfxbq.mexicocentral-01.azurewebsites.net/api/ "
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
}
