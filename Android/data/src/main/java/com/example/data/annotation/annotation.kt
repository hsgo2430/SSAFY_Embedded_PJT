package com.example.data.annotation

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class  NoAuth


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class  ServerUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class  ServerRetrofit