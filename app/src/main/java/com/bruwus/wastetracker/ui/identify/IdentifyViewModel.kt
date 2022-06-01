package com.bruwus.wastetracker.ui.identify

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bruwus.wastetracker.ui.identify.data.IdentifyWaste
import com.bruwus.wastetracker.ui.identify.data.entities.IdentifyWasteResult
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class IdentifyViewModel : ViewModel() {
    private val identifyWaste =  IdentifyWaste()

    private val _wasteIdentification = MutableLiveData<IdentifyWasteResult?>()
    val wasteIdentification: LiveData<IdentifyWasteResult?> get() = _wasteIdentification

    var imageBitmap: Bitmap? = null

    fun identifyWaste(imageUrl: String, storageReference: StorageReference) {
        viewModelScope.launch {
            identifyWaste.identifyWasteByImage(imageUrl)
                .catch {  exception ->
                    Log.v("IdentifyApiException", exception.message!!)
                    _wasteIdentification.postValue(null)
                }
                .collect { identifyWasteResult ->
                    _wasteIdentification.postValue(identifyWasteResult)
                }

            storageReference.delete()
        }
    }

    fun uploadPhotoToFirebase(imageBitmap: Bitmap, onSuccess: (imageName: StorageReference, imageUrl: String) -> Unit, onFailure: () -> Unit) {
        val imageId = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val imageName = imageId.format(Date())

        val waitTime = 10000.toLong()
        val firebaseStorage = FirebaseStorage.getInstance()
        firebaseStorage.maxOperationRetryTimeMillis = waitTime
        firebaseStorage.maxUploadRetryTimeMillis = waitTime
        firebaseStorage.maxDownloadRetryTimeMillis = waitTime

        val storageReference = firebaseStorage.getReference("identifyWaste/${imageName}")
        val bytes = ByteArrayOutputStream()

        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes)
        storageReference.putBytes(bytes.toByteArray())
            .addOnSuccessListener { snapshot ->
                snapshot.storage.downloadUrl.addOnCompleteListener { task ->
                    val imageUrl = task.result.toString()

                    onSuccess(storageReference, imageUrl)
                }
            }
            .addOnFailureListener {
                onFailure()
            }
    }
}