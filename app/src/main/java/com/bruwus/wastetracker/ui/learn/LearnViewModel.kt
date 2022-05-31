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
    private val wasteTypes: LiveData<List<WasteType>> get() = _wasteTypes

    private val _recycleTips = MutableLiveData<List<RecycleTip>>()
    private val recycleTips: LiveData<List<RecycleTip>> get() = _recycleTips

    private val _tools3D = MutableLiveData<List<Tool3D>>()
    private val tools3D: LiveData<List<Tool3D>> get() = _tools3D

    private var locale: String = ""

    val lists = listOf(
        wasteTypes,
        recycleTips,
        tools3D
    )

    fun fetchAllData() {
        val newLocale = Locale.getDefault().language

        if (locale != newLocale) {
            locale = newLocale

            fetchData("wasteType/language/${locale}", _wasteTypes)
            fetchData("recycleTip/language/${locale}", _recycleTips)
            fetchData("tool3D/language/${locale}", _tools3D)
        }
    }

    private inline fun <reified T> fetchData(referencePath: String, list: MutableLiveData<List<T>>) {
        databaseReference.collection(referencePath).get()
            .addOnSuccessListener { snapshot ->
                val dataList = mutableListOf<T>()

                snapshot.documents.forEach { data ->
                    data.toObject<T>()?.let { dataList.add(it) }
                }

                list.postValue(dataList)
            }
            .addOnFailureListener { exception ->
                Log.v("FirebaseException", exception.message!!)
                list.postValue(null)
            }
    }
}