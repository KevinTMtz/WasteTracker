package com.bruwus.wastetracker.ui.learn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bruwus.wastetracker.ui.learn.data.RecycleTip
import com.bruwus.wastetracker.ui.learn.data.Tool3D
import com.bruwus.wastetracker.ui.learn.data.WasteType
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.*

class LearnViewModel : ViewModel() {
    private val databaseReference = Firebase.firestore

    private val _wasteTypes = MutableLiveData<List<WasteType>>()
    val wasteTypes: LiveData<List<WasteType>> get() = _wasteTypes

    private val _recycleTips = MutableLiveData<List<RecycleTip>>()
    val recycleTips: LiveData<List<RecycleTip>> get() = _recycleTips

    private val _tools3D = MutableLiveData<List<Tool3D>>()
    val tools3D: LiveData<List<Tool3D>> get() = _tools3D

    private val locale = Locale.getDefault().language

    fun initViewModel() {
        fetchWasteTypes()
        fetchRecycleTips()
        fetchTools3D()
    }

    private fun fetchWasteTypes() {
        databaseReference.collection("wasteType/language/${locale}").get()
            .addOnSuccessListener { snapshot ->
                val wasteTypeList = mutableListOf<WasteType>()

                snapshot.documents.forEach { wasteType ->
                    wasteType.toObject<WasteType>()?.let { wasteTypeList.add(it) }
                }

                _wasteTypes.postValue(wasteTypeList)
            }
            .addOnFailureListener { exception ->
                Log.v("FirebaseException", exception.message!!)
            }
    }

    private fun fetchRecycleTips() {
        databaseReference.collection("recycleTip/language/${locale}").get()
            .addOnSuccessListener { snapshot ->
                val recycleTipList = mutableListOf<RecycleTip>()

                snapshot.documents.forEach { recycleTip ->
                    recycleTip.toObject<RecycleTip>()?.let { recycleTipList.add(it) }
                }

                _recycleTips.postValue(recycleTipList)
            }
            .addOnFailureListener { exception ->
                Log.v("FirebaseException", exception.message!!)
            }
    }

    private fun fetchTools3D() {
        databaseReference.collection("tool3D/language/${locale}").get()
            .addOnSuccessListener { snapshot ->
                val tool3DList = mutableListOf<Tool3D>()

                snapshot.documents.forEach { tool3D ->
                    tool3D.toObject<Tool3D>()?.let { tool3DList.add(it) }
                }

                _tools3D.postValue(tool3DList)
            }
            .addOnFailureListener { exception ->
                Log.v("FirebaseException", exception.message!!)
            }
    }
}