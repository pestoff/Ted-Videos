package ru.pestoff.tedvideos.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.pestoff.tedvideos.adapter.VideoAdapter
import ru.pestoff.tedvideos.databinding.ListVideoFragmentBinding
import ru.pestoff.tedvideos.model.Item
import ru.pestoff.tedvideos.contract.ListVideoContract
import ru.pestoff.tedvideos.presenter.ListVideoPresenter
import java.lang.ClassCastException

class ListVideoFragment : Fragment(), ListVideoContract.View {

    interface FragmentInteractionListener {
        fun onItemChosen(item: Item)
    }

    private var _binding: ListVideoFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: FragmentInteractionListener
    private lateinit var adapter: VideoAdapter
    private lateinit var presenter: ListVideoPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = ListVideoFragmentBinding.inflate(inflater, container, false)

        presenter = ListVideoPresenter(this)

        initRecyclerView()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteractionListener) {
            listener = context
        } else {
            throw ClassCastException("$context must implement FragmentInteractionListener")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        adapter = VideoAdapter(object : VideoAdapter.OnClickListener {
            override fun onClick(item: Item) {
                listener.onItemChosen(item)
            }
        })

        binding.recyclerView.adapter = adapter

        presenter.loadDataFromRss()
    }

    override fun getAdapter() = adapter
}
