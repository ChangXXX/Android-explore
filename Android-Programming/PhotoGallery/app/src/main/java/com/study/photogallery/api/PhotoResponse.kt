package com.study.photogallery.api

import com.google.gson.annotations.SerializedName
import com.study.photogallery.GalleryItem

class PhotoResponse {
    @SerializedName("photo")
    lateinit var galleryItems: List<GalleryItem>
}
