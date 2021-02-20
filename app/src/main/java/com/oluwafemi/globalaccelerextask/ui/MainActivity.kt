package com.oluwafemi.globalaccelerextask.ui

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.oluwafemi.globalaccelerextask.*
import com.oluwafemi.globalaccelerextask.databinding.ActivityMainBinding
import com.oluwafemi.globalaccelerextask.domain.CardDetails
import com.oluwafemi.globalaccelerextask.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subScribeObserver()
        val cardNumberLayout = binding.cardNumber

        cardNumberLayout.setEndIconOnClickListener {
            binding.progressBar.makeVisible()

            if (isOnline(baseContext)) {
                if (cardNumberLayout.error.isNullOrEmpty()) {
                    viewModel.getDetails(cardNumberLayout.editText?.text.toString().toLong())
                }
            } else {
                binding.progressBar.makeGone()
                Snackbar.make(
                    binding.cardNumber,
                    getString(R.string.no_internet),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        cardNumberLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val cardNumber = cardNumberLayout.editText?.text.toString().toInt()

                if (p0 != null && cardNumber in 6..9) {
                    cardNumberLayout.error = ""
                } else {
                    cardNumberLayout.error = getString(R.string.error_msg)
                }
            }

        })
    }

    private fun subScribeObserver() {
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is NetworkResult.Success<CardDetails> -> {
                    val cardDetails = dataState.data
                    binding.progressBar.makeGone()
                    binding.cardDetails.makeVisible()
                    binding.cardDetails.text = getString(R.string.details,
                        cardDetails.cardScheme,
                        cardDetails.cardType,
                        cardDetails.bank,
                        cardDetails.country,
                        cardDetails.cardLengthNumber.toString(),
                        if (cardDetails.prepaid) "Prepaid" else "Postpaid")
                }
                is NetworkResult.Error -> {
                    binding.progressBar.makeGone()
                    Snackbar.make(
                        binding.cardNumber,
                        getString(R.string.something_went_wrong),
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                else -> {
                    binding.progressBar.makeVisible()
                }
            }
        })
    }
}