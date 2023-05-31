package com.sp.keyoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sp.keyoapp.R
import com.sp.keyoapp.baseobservable.EnrollmentFragmentBaseObservable
import com.sp.keyoapp.databinding.FragmentEnrollmentBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EnrollmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnrollmentFragment : Fragment() {

    private lateinit var baseObservable: EnrollmentFragmentBaseObservable
    private var activityCallback: ActivityCallback? = null
    private var tag: String = ""

    private var actionCallback = object : EnrollmentFragmentBaseObservable.ActionCallback {
        override fun onClick(view: View) {
            activityCallback?.onClick(tag)
        }
    }

    lateinit var headline: String
    lateinit var bodyTxt: String
    var icon: Int = 1
    var showGradient: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity !is ActivityCallback) {
            throw Exception("Activity must implement ActivityCallback.")
        }
        arguments?.let {
            headline = it.getString(KEY_ARGS_HEADLINE) ?: ""
            bodyTxt = it.getString(KEY_ARGS_BODY_TXT) ?: ""
            icon = it.getInt(KEY_ARGS_ICON)
            showGradient = it.getBoolean(KEY_ARGS_SHOW_GRADIENT)
            tag = it.getString(KEY_ARGS_TAG)?:""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentEnrollmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_enrollment, container, false)
        baseObservable = EnrollmentFragmentBaseObservable(headline, bodyTxt, icon, showGradient)
        binding.data = baseObservable
        binding.callback = actionCallback
        return binding.root
    }

    fun setGradient(value: Boolean) {
        baseObservable.showGradient = value
    }

    fun setActivityCallback(context: ActivityCallback) {
        activityCallback = context
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EnrollmentFragment.
         */
        // TODO: Rename and change types and number of parameters

        private const val KEY_ARGS_HEADLINE = "KEY_ARGS_HEADLINE"
        private const val KEY_ARGS_BODY_TXT = "KEY_ARGS_BODY_TXT"
        private const val KEY_ARGS_ICON = "KEY_ARGS_ICON"
        private const val KEY_ARGS_SHOW_GRADIENT = "KEY_ARGS_SHOW_GRADIENT"
        private const val KEY_ARGS_TAG = "KEY_ARGS_TAG"

        @JvmStatic
        fun newInstance(
            tag: String,
            headline: String,
            bodyTxt: String,
            icon: Int,
            showGradient: Boolean
        ) =
            EnrollmentFragment().apply {
                arguments = Bundle().apply {
                    this.putString(KEY_ARGS_HEADLINE, headline)
                    this.putString(KEY_ARGS_BODY_TXT, bodyTxt)
                    this.putInt(KEY_ARGS_ICON, icon)
                    this.putBoolean(KEY_ARGS_SHOW_GRADIENT, showGradient)
                    this.putString(KEY_ARGS_TAG, tag)
                }
            }
    }

    interface ActivityCallback {
        fun onClick(tag: String)
    }
}