package com.kanulp.buisnesslistapp

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kanulp.buisnesslistapp.model.Business
import com.kanulp.buisnesslistapp.viewmodel.BusinessSearchViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var textView : TextView? = null
    private lateinit var businessSearchViewModel: BusinessSearchViewModel
    private var auto_complete_text : AutoCompleteTextView? = null
//    private val TRIGGER_AUTO_COMPLETE = 100
//    private val AUTO_COMPLETE_DELAY: Long = 300
//    private var handler: Handler? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_first, container, false)
        textView =  v.findViewById(R.id.tv_data)
        auto_complete_text = v.findViewById(R.id.auto_complete_text)
        auto_complete_text?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("FirstFrag","$p0")
                if(p0?.length!! > 2){
                    businessSearchViewModel.getBusiness(p0.toString(),"43.696420","-79.541940")
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        auto_complete_text?.setOnItemClickListener() { parent, _, position, id ->
            val selectedPoi = parent.adapter.getItem(position) as Business?
            auto_complete_text?.setText(selectedPoi?.name)

        }
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        businessSearchViewModel = ViewModelProvider(requireActivity()).get(BusinessSearchViewModel::class.java)
        registerObserver()
        //businessSearchViewModel.getBusiness("petro","43.696420","-79.541940")
    }

    private fun registerObserver(){
        businessSearchViewModel?.businessSearchSuccessLiveData?.observe(viewLifecycleOwner, {
            try {
                val adapter = AutoCompleteAdapter(
                    requireActivity(),
                    android.R.layout.simple_list_item_1,
                    it?.businesses
                )
                auto_complete_text?.setAdapter(adapter)
                auto_complete_text?.threshold = 3

            } catch (e: Exception) {
                e.printStackTrace()
            }
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