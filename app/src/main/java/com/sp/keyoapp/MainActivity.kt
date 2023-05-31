package com.sp.keyoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sp.keyoapp.databinding.ActivityMainBinding
import com.sp.keyoapp.fragment.EnrollmentFragment
import com.sp.keyoapp.fragment.PlaceholderFragment

class MainActivity : AppCompatActivity(), EnrollmentFragment.ActivityCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var enrollmentFragment : EnrollmentFragment
    private lateinit var idFragment : EnrollmentFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpFragments()
    }

    private fun setUpFragments() {
        //1. set up enrollment Fragment
        enrollmentFragment = EnrollmentFragment.newInstance(
            headline = "Enrollment",
            bodyTxt = "Quickly enroll your members.",
            icon = R.drawable.ic_palm,
            showGradient = true,
            tag = ENROLL_FRAGMENT_TAG
        )
        enrollmentFragment.setActivityCallback(this)
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fl_first_card,
                enrollmentFragment,
                "${EnrollmentFragment::class.java.simpleName}- 1"
            )
            .commit()
        // 2. set up id fragment
        idFragment = EnrollmentFragment.newInstance(
            headline = "ID",
            bodyTxt = "Have your members identify themselves in under a second.",
            icon = R.drawable.ic_user,
            showGradient = false,
            tag = ID_FRAGMENT_TAG
        )
        idFragment.setActivityCallback(this)
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_second_card, idFragment, "${EnrollmentFragment::class.java.simpleName}- 2")
            .commit()
        //3. set up placeholder fragment
        val placeholderFragment = PlaceholderFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .add(
                R.id.fl_third_card,
                placeholderFragment,
                PlaceholderFragment::class.java.simpleName
            )
            .commit()

    }

    override fun onClick(tag: String) {
        if(tag == ENROLL_FRAGMENT_TAG){
            enrollmentFragment.setGradient(false)
            idFragment.setGradient(true)
        }else{
            enrollmentFragment.setGradient(true)
            idFragment.setGradient(false)
        }
    }

    companion object{
        private const val ENROLL_FRAGMENT_TAG = "ENROLL_FRAGMENT_TAG"
        private const val ID_FRAGMENT_TAG = "ID_FRAGMENT_TAG"
    }
}