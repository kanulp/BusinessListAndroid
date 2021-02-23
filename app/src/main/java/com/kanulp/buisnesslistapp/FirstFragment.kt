package com.kanulp.buisnesslistapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kanulp.buisnesslistapp.viewmodel.BusinessSearchViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var textView : TextView? = null
    private lateinit var businessSearchViewModel: BusinessSearchViewModel
    private var auto_complete_text : AutoCompleteTextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_first, container, false)
        textView =  v.findViewById(R.id.tv_data)
        auto_complete_text = v.findViewById(R.id.auto_complete_text)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        businessSearchViewModel = ViewModelProvider(requireActivity()).get(BusinessSearchViewModel::class.java)
        registerObserver()
        businessSearchViewModel.getBusiness("honest","43.696420","-79.541940")
    }

    private fun registerObserver(){
        businessSearchViewModel?.businessSearchSuccessLiveData?.observe(viewLifecycleOwner, {
            Log.d(
                "FirstFragment",
                "${it.value?.businesses?.size} - ${it.value?.businesses?.get(0)?.name}"
            )
            textView?.text = it.value?.businesses?.get(0)?.name
        })
    }


//    private fun initLocation() {
//        if (checkAndRequestPermissions(context!!)) {
//            Toast.makeText(context, "Permission Granded", Toast.LENGTH_SHORT).show()
//        }
//    }
//    fun checkAndRequestPermissions(context: Context): Boolean {
//        val locationPermission = ContextCompat.checkSelfPermission(
//            context,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        )
//        val listPermissionsNeeded: MutableList<String> = ArrayList()
//        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
//            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//        if (!listPermissionsNeeded.isEmpty()) {
//            ActivityCompat.requestPermissions(
//                (context as Activity?)!!,
//                listPermissionsNeeded.toTypedArray(),
//                REQUEST_ID_MULTIPLE_PERMISSIONS
//            )
//            return false
//        }
//        return true
//    }
}