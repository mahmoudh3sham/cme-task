package com.cme.task.utils

object ImageHQ {
    
    fun getImageHQ(imageUrl: String?) : String? {
        var image = imageUrl
        if (imageUrl?.contains("100x100bb") == true){
            image = imageUrl.replace("100x100", "500x500")
        }
        return image
    }
}