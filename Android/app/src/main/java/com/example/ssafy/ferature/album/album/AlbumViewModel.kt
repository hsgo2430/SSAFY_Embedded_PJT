package com.example.ssafy.ferature.album.album

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.model.ImageGroups
import com.example.domain.usecase.image.GetImageGroupsUseCase
import com.example.ssafy.ferature.setting.setting.SettingSideEffect
import com.example.ssafy.ferature.setting.setting.SettingState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getImageGroupsUseCase: GetImageGroupsUseCase
): ViewModel(), ContainerHost<AlbumState, AlbumSideEffect> {

    override val container: Container<AlbumState, AlbumSideEffect> = container<AlbumState, AlbumSideEffect> (AlbumState())


    init{
        getImageGroups()
    }

    private fun getImageGroups() = intent{
        getImageGroupsUseCase()
            .onSuccess {
                reduce {
                    state.copy(
                        imageGroups = it
                    )
                }
                Log.e("로그", state.imageGroups.toString())
            }
            .onFailure {
                Log.e("로그", "실패")
            }
    }

}