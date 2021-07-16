package com.kuzheevadel.radioplayerv2.radio.popularradio

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.kuzheevadel.radioplayerv2.radio.MainRadioFragment
import com.kuzheevadel.radioplayerv2.radio.RadioViewModel
import javax.inject.Inject

class PopularRadioFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RadioViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireParentFragment() as MainRadioFragment).radioComponent
                .inject(this)
    }
}