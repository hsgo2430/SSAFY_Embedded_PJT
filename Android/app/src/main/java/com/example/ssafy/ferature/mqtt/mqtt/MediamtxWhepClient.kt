package com.example.ssafy.ferature.mqtt.mqtt

import android.util.Log
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.webrtc.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MediamtxWhepClient(
    private val renderer: SurfaceViewRenderer,
    private val eglBase: EglBase   // 🔥 외부에서 전달받음
) {
    private val http = OkHttpClient()
    private lateinit var pc: PeerConnection
    private lateinit var factory: PeerConnectionFactory

    init {
        PeerConnectionFactory.initialize(
            PeerConnectionFactory.InitializationOptions.builder(renderer.context)
                .createInitializationOptions()
        )

        factory = PeerConnectionFactory.builder()
            .setVideoDecoderFactory(DefaultVideoDecoderFactory(eglBase.eglBaseContext)) // 🔥 UI EGL 사용
            .createPeerConnectionFactory()
    }

    suspend fun start(whepUrl: String) = withContext(Dispatchers.IO) {
        try {
            val config = PeerConnection.RTCConfiguration(
                listOf(
                    PeerConnection.IceServer.builder("stun:stun.l.google.com:19302").createIceServer()
                )
            ).apply {
                sdpSemantics = PeerConnection.SdpSemantics.UNIFIED_PLAN
            }

            pc = factory.createPeerConnection(config, object : PeerConnection.Observer {
                override fun onSignalingChange(newState: PeerConnection.SignalingState?) {

                }

                override fun onIceConnectionChange(newState: PeerConnection.IceConnectionState?) {

                }

                override fun onIceConnectionReceivingChange(receiving: Boolean) {

                }

                override fun onIceGatheringChange(newState: PeerConnection.IceGatheringState?) {

                }

                override fun onIceCandidate(candidate: IceCandidate?) {

                }

                override fun onIceCandidatesRemoved(candidates: Array<out IceCandidate>?) {

                }

                override fun onAddStream(stream: MediaStream?) {

                }

                override fun onRemoveStream(stream: MediaStream?) {

                }

                override fun onDataChannel(dataChannel: DataChannel?) {

                }

                override fun onRenegotiationNeeded() {

                }

                override fun onAddTrack(receiver: RtpReceiver?, mediaStreams: Array<out MediaStream>?) {
                    val track = receiver?.track() as? VideoTrack ?: return
                    track.addSink(renderer) // 🔥 이제 화면에 보임
                }
            }) ?: throw RuntimeException("PeerConnection creation failed")

            // --- Offer 생성 ---
            val offer = suspendCoroutine<SessionDescription> { cont ->
                pc.createOffer(object : SdpObserver {
                    override fun onCreateSuccess(desc: SessionDescription?) {
                        cont.resume(desc!!)
                    }
                    override fun onCreateFailure(msg: String?) {
                        cont.resumeWith(Result.failure(Exception(msg)))
                    }
                    override fun onSetSuccess() {}
                    override fun onSetFailure(p0: String?) {}
                }, MediaConstraints().apply {
                    mandatory.add(MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"))
                })
            }

            suspendCoroutine<Unit> { cont ->
                pc.setLocalDescription(object : SdpObserver {
                    override fun onSetSuccess() = cont.resume(Unit)
                    override fun onSetFailure(msg: String?) =
                        cont.resumeWith(Result.failure(Exception(msg)))
                    override fun onCreateSuccess(p0: SessionDescription?) {}
                    override fun onCreateFailure(p0: String?) {}
                }, offer)
            }

            // --- Send to WHEP ---
            val answerSdp = sendOfferToWhep(whepUrl, offer.description)

            // --- Set Remote Description ---
            val answer = SessionDescription(SessionDescription.Type.ANSWER, answerSdp)

            suspendCoroutine<Unit> { cont ->
                pc.setRemoteDescription(object : SdpObserver {
                    override fun onSetSuccess() = cont.resume(Unit)
                    override fun onSetFailure(msg: String?) =
                        cont.resumeWith(Result.failure(Exception(msg)))
                    override fun onCreateSuccess(p0: SessionDescription?) {}
                    override fun onCreateFailure(p0: String?) {}
                }, answer)
            }

        } catch (e: Exception) {
            Log.e("WHEP", "start() 실패", e)
        }
    }

    private fun sendOfferToWhep(whepUrl: String, offerSdp: String): String {
        val body = offerSdp.toByteArray().toRequestBody("application/sdp".toMediaType())

        val req = Request.Builder()
            .url(whepUrl)
            .addHeader("Accept", "application/sdp")
            .post(body)
            .build()

        return http.newCall(req).execute().use { res ->
            if (!res.isSuccessful) throw RuntimeException("WHEP Offer failed ${res.code}")
            res.body!!.string()
        }
    }

    fun release() {
        try {
            renderer.release()
            pc.close()
        } catch (_: Exception) {}
    }
}

