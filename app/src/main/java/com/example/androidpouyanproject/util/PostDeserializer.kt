package com.example.androidpouyanproject.util

import com.example.androidpouyanproject.data.entity.Post
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class PostDeserializer: JsonDeserializer<List<Post>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<Post> {
        val jsonObject = json?.asJsonObject
        val postsJsonArray = jsonObject?.getAsJsonArray("posts")
        val posts = mutableListOf<Post>()
        postsJsonArray?.forEach{ postElement ->
            val postObject = postElement.asJsonObject
            val thumbnailUrl = postObject.getAsJsonPrimitive("full_thumbnail_url").asString
            val caption = postObject.getAsJsonPrimitive("caption").asString
            val likesCount = postObject.getAsJsonPrimitive("likes_count").asInt
            val commentsCount = postObject.getAsJsonPrimitive("comments_count").asInt

            val post = Post(
                full_thumbnail_url = thumbnailUrl,
                caption = caption,
                likesCount = likesCount,
                commentsCount = commentsCount
            )

            posts.add(post)
        }

        return posts

    }

}