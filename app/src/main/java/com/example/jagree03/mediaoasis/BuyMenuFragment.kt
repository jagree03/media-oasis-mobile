package com.example.jagree03.mediaoasis

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.findViewTreeLifecycleOwner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BuyMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BuyMenuFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_buy_menu, container, false)
    }

    // when the fragment is selected in the framelayout container and the overall view has been created..
    // then do these operations.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Accessing the elements inside the view of this fragment
        val purchaseButton: Button = view.findViewById(R.id.button_purchase)

        setupSpinner(view) // setup the spinner's items using ArrayAdapter.

        purchaseButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Toast.makeText(activity, "Added to cart", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment BuyMenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BuyMenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /**
     * This code setups the spinner by attaching an arrayAdapter
     * Code reference: https://www.youtube.com/watch?v=lAckLFH7mIE
     */
    fun setupSpinner(view: View) {
        val spinnerItems = arrayOf("Movies", "Video Games", "TV Show Sets")
        val spinner = view.findViewById<Spinner>(R.id.spinnerItemSelect)
        val arrayAdapterSpinner = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, spinnerItems)
        spinner.adapter = arrayAdapterSpinner
    }

    fun addToCart() {
        Toast.makeText(activity, "Added to cart", Toast.LENGTH_SHORT)
    }

    /**
     * This method starts the Shopping Cart Activity, passing along data of items added to the cart.
     */
    fun goToShoppingCartActivity() {
        val send = Intent(activity, ShoppingCartActivity::class.java)

        startActivity(send)
    }
}