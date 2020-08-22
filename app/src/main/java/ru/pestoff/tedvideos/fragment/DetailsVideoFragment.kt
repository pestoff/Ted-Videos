package ru.pestoff.tedvideos.fragment

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.pestoff.tedvideos.R
import ru.pestoff.tedvideos.databinding.DetailsVideoFragmentBinding
import ru.pestoff.tedvideos.model.Item
import ru.pestoff.tedvideos.contract.DetailsViewContract
import ru.pestoff.tedvideos.presenter.DetailsViewPresenter
import ru.pestoff.tedvideos.util.StringUtil

class DetailsVideoFragment: Fragment(), DetailsViewContract.View {

    private var _binding: DetailsVideoFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: DetailsViewContract.Presenter

    private var item: Item? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsVideoFragmentBinding.inflate(inflater, container, false)

        arguments?.let {
            item = it.getParcelable(ITEM_KEY) as Item?
        }

        presenter = DetailsViewPresenter(this)

        item?.let {
            init(it)
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            presenter.releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            presenter.releasePlayer()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun init(item: Item) {

        binding.detailsTitle.text = StringUtil.getThemeFromTitle(item.title)
        binding.detailsAuthors.text =
            StringUtil.getAuthorFromTitle(item.title)
        binding.detailsDescription.text = item.description

        if (isDarkMode()) {
            binding.likeImage.setImageResource(R.drawable.like_anim_dark)
            binding.shareImage.setImageResource(R.drawable.share_anim_dark)
        } else {
            binding.likeImage.setImageResource(R.drawable.like_anim_light)
            binding.shareImage.setImageResource(R.drawable.share_anim_light)
        }

        binding.likeImage.setOnClickListener {
            (binding.likeImage.drawable as AnimatedVectorDrawable).start()
        }

        binding.shareImage.setOnClickListener {
            (binding.shareImage.drawable as AnimatedVectorDrawable).start()
        }

        initMediaPlayer(item.group.contents[0].url)
    }


    private fun isDarkMode() : Boolean {
        val preferences = this.activity?.getSharedPreferences("AppSettingsPrefs", 0)
        return preferences?.getBoolean("NightMode", false)!!
    }

    private fun initMediaPlayer(url: String) {
        binding.videoView.player = presenter
            .getPlayer()
            .getPlayerImpl(requireActivity().applicationContext)

        presenter.play(url)
    }

    companion object {

        private val ITEM_KEY = "Item"

        fun createInstance(item: Item): DetailsVideoFragment {
            return DetailsVideoFragment().apply {
                arguments = bundleOf(
                    ITEM_KEY to item
                )
            }
        }
    }
}