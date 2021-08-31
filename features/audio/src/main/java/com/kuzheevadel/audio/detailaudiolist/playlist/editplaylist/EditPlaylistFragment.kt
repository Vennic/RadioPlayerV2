package com.kuzheevadel.audio.detailaudiolist.playlist.editplaylist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kuzheevadel.audio.AudioNavHostFragment
import com.kuzheevadel.audio.R
import com.kuzheevadel.audio.databinding.AddAudioFragmentBinding

import javax.inject.Inject

class EditPlaylistFragment: Fragment(), OnStartDragListener {

    private lateinit var mItemTouchHelper: ItemTouchHelper
    lateinit var editAdapter: EditPlaylistAdapter
    private lateinit var binding: AddAudioFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<EditPlaylistViewModel> { viewModelFactory }

    private val args: EditPlaylistFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireParentFragment() as AudioNavHostFragment).audioComponent
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddAudioFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)


        binding.addAudioToolbar.setupWithNavController(navController, appBarConfiguration)

        binding.addAudioToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.apply_add_audio -> {
                    viewModel
                        .editPlaylistComplete(editAdapter.getAudioList(), args.playlistPosition)
                    findNavController().navigateUp()
                    true
                }
                else -> false
            }
        }
        binding.addAudioToolbar.menu.findItem(R.id.apply_add_audio).isVisible = true

        setUpRecyclerView()
        editAdapter.setAudioList(viewModel.getAudioList(args.playlistPosition))
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let {
            mItemTouchHelper.startDrag(it)
        }
    }

    override fun onStartSwipe(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let {
            mItemTouchHelper.startSwipe(it)
        }
    }

    private fun setUpRecyclerView() {

        editAdapter = EditPlaylistAdapter(this)
        binding.apply {
            addAudioRecycler.layoutManager = LinearLayoutManager(requireContext())
            addAudioRecycler.adapter = editAdapter
        }
        val callback: ItemTouchHelper.Callback = ReorderHelperCallback(editAdapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(binding.addAudioRecycler)
    }

}