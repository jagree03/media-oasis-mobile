package com.example.jagree03.mediaoasis

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RatingBar
import androidx.core.widget.doOnTextChanged

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Admin_ManageReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Admin_ManageReviewsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin__manage_reviews, container, false)
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextStarRatingValue = view.findViewById<EditText>(R.id.editTextStarRatingValue)
        val ratingBarReviewRating = view.findViewById<RatingBar>(R.id.ratingBarReviewRating)

        /**
         * This code was adapted from a stack overflow post https://stackoverflow.com/questions/40569436/kotlin-addtextchangelistener-lambda
         * which discusses the use of the addTextChangedListener within Kotlin.
         * It uses a TextWatcher object that has an interface containing 3 methods.
         * Modifying the onTextChanged() method allows you to perform specific behaviours when the text is modified in the EditText element.
         */
        editTextStarRatingValue.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) { }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = editTextStarRatingValue.text.toString()

                if (text.isNotEmpty()) {
                    ratingBarReviewRating.rating = text.toFloat()
                }

            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Admin_ManageReviewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Admin_ManageReviewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}