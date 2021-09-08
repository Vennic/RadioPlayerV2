package com.kuzheevadel.playback

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kuzheevadel.core.common.MediaType
import com.kuzheevadel.core.models.Audio
import com.kuzheevadel.playback.databinding.PlaybackLayoutBinding
import com.kuzheevadel.playback.di.PlaybackComponentProvider
import com.kuzheevadel.ui.SlidePanelActivity
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaybackFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<PlaybackViewModel> { viewModelFactory }

    private var _binding: PlaybackLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as PlaybackComponentProvider)
            .getPlaybackComponent()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlaybackLayoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.playbackControlsLayout) {
            nextAudioButton.setOnClickListener { viewModel.onNextAudio() }
            previousAudioButton.setOnClickListener { viewModel.onPreviousAudio() }
            playOrPauseButton.setOnClickListener { viewModel.onPlayButtonClicked() }
            playbackAudioTitle.isSelected = true
        }

        binding.topPlaybackLayout.playOrPauseImageButton
            .setOnClickListener { viewModel.onPlayButtonClicked() }

        setSlidingPanelListener()
        subscribeUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun subscribeUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentMediaFLow.collect { mediaType ->
                    if (mediaType is MediaType.Audio) {
                        binding.audio = mediaType.audio
                        setPlayOrPauseButtonImage(mediaType.audio)
                    }
                }
            }
        }
    }

    private fun setPlayOrPauseButtonImage(audio: Audio) {
        if (audio.isPlaying) {
            binding.topPlaybackLayout.playOrPauseImageButton
                .setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)

            binding.playbackControlsLayout.playOrPauseButton
                .setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)
        } else {
            binding.topPlaybackLayout.playOrPauseImageButton
                .setImageResource(R.drawable.ic_baseline_play_circle_filled_24)

            binding.playbackControlsLayout.playOrPauseButton
                .setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
        }
    }

    private fun setSlidingPanelListener() {
        val slidePanel = (requireActivity() as SlidePanelActivity).getSlidePanelLayoutInst()

        slidePanel.addPanelSlideListener(object: SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
                binding.topPlaybackLayout.apply {
                    playOrPauseImageButton.alpha = 1 - slideOffset
                    topPlaybackImageView.alpha = 1 - slideOffset
                    topPlaybackInfoText.alpha = 1 - slideOffset

                    if (slideOffset.toInt() == 1) {
                        playOrPauseImageButton.visibility = View.GONE
                        horizontalLineImageView.visibility = View.VISIBLE
                    } else {
                        playOrPauseImageButton.visibility = View.VISIBLE
                        horizontalLineImageView.visibility = View.GONE
                    }
                }
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState?
            ) {
                //do nothing
            }

        })
    }
}