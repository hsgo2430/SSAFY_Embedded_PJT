package com.example.data.di.network


import android.util.Base64
import okhttp3.Interceptor
import okhttp3.Response
import okio.Buffer
import java.security.MessageDigest
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class ServerHmacInterceptor(
    private val apiKey: String,
    private val hmacSecretB64: String,
    private val deviceId: String = "raspi-01"
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()

        val ts = (System.currentTimeMillis() / 1000L).toString() // ✅ 서버는 초 단위(time.time())
        val method = req.method.uppercase()
        val path = req.url.encodedPath // ✅ 서버는 request.url.path (쿼리 제외)

        // 서버는 content_sha를 실제 바디랑 비교하진 않지만, 서명 문자열에 포함되므로 "일관되게" 만들기
        val contentSha = sha256HexOfBodySafe(req)

        val stringToSign = "$deviceId\n$ts\n$method\n$path\n$contentSha"
        val signature = hmacSha256Base64UrlNoPad(hmacSecretB64, stringToSign)

        val newReq = req.newBuilder()
            .header("X-API-Key", apiKey)
            .header("X-Device-Id", deviceId)
            .header("X-Timestamp", ts)
            .header("X-Content-SHA256", contentSha)
            .header("X-Signature", signature)
            .build()

        return chain.proceed(newReq)
    }

    private fun sha256HexOfBodySafe(req: okhttp3.Request): String {
        val body = req.body ?: return sha256Hex(ByteArray(0))
        return try {
            val buffer = Buffer()
            body.writeTo(buffer)               // multipart면 메모리 부담될 수 있음
            sha256Hex(buffer.readByteArray())
        } catch (_: Throwable) {
            // 업로드 같은 큰 요청에서 실패/부담이면 빈 바디 해시로 fallback (서버는 현재 바디 검증 안 함)
            sha256Hex(ByteArray(0))
        }
    }

    private fun sha256Hex(bytes: ByteArray): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hash = md.digest(bytes)
        return hash.joinToString("") { "%02x".format(it) }
    }

    // 서버 b64url_decode와 호환: urlsafe + 패딩 자동 보정
    private fun base64UrlDecodeToBytes(s: String): ByteArray {
        val cleaned = s.trim()
        val normalized = cleaned
            .replace('-', '+')
            .replace('_', '/')
        val pad = "=".repeat((4 - normalized.length % 4) % 4)
        return Base64.decode(normalized + pad, Base64.DEFAULT)
    }

    // 서버 b64url_encode와 호환: URL_SAFE + '=' 제거
    private fun base64UrlEncodeNoPad(bytes: ByteArray): String {
        val out = Base64.encodeToString(bytes, Base64.URL_SAFE or Base64.NO_WRAP)
        return out.trimEnd('=')
    }

    private fun hmacSha256Base64UrlNoPad(secretB64: String, data: String): String {
        val secretBytes = base64UrlDecodeToBytes(secretB64)
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secretBytes, "HmacSHA256"))
        val raw = mac.doFinal(data.toByteArray(Charsets.UTF_8))
        return base64UrlEncodeNoPad(raw)
    }
}
