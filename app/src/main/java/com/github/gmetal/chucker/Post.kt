package com.github.gmetal.chucker

import com.bluelinelabs.logansquare.annotation.JsonField
import com.bluelinelabs.logansquare.annotation.JsonObject
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
@JsonObject
class Post {
    @get:Json(name = "id")
    @JsonField
    var id: Int = 0

    @get:Json(name = "userId")
    @JsonField
    var userId: Int = 0

    @get:Json(name = "title")
    @JsonField
    var title: String = ""

    @get:Json(name = "body")
    @JsonField
    var body: String = ""
}