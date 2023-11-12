package com.htetarkarzaw.codigouidesign.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.htetarkarzaw.codigouidesign.R
import com.htetarkarzaw.codigouidesign.databinding.FragmentHomeBinding
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val bannerAdapter by lazy {
        BannerAdapter {
            findNavController().navigate(R.id.action_navigation_home_to_detailBSFragment)
        }
    }
    private val cardAdapter by lazy {
        CardAdapter {
            findNavController().navigate(R.id.action_navigation_home_to_detailBSFragment)
        }
    }

    private val showAdapter by lazy {
        ShowAdapter {
            findNavController().navigate(R.id.action_navigation_home_to_detailBSFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        setUpRecyclerViews()
    }

    private fun setUpRecyclerViews() {
        val cardList = mutableListOf(
            CardVO("My e-tickets", "There aren't any yet.", "Retrieve here", R.drawable.ic_card_1),
            CardVO("Park hours", "Today, 13 Feb 10am - 5pm", "Plan my visit", R.drawable.ic_card_2)
        )
        val showList = mutableListOf(
            ShowVO("Dive Feeding @ Shipwreck", "2:30pm", R.drawable.img_aquarium_1),
            ShowVO("Say Cheese with Shark @ Shipwreck", "4:30pm", R.drawable.img_aquariam_2),
            ShowVO("Water Polo @ Shipwreck", "6:30pm", R.drawable.img_aquarium_3)
        )
        cardAdapter.apply {
            binding.rvCard.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            binding.rvCard.adapter = this
            submitList(cardList)
        }

        showAdapter.apply {
            binding.rvShow.layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL, false)
            binding.rvShow.adapter = this
            submitList(showList)
        }
    }

    private fun setUpViewPager() {
        val imageList = mutableListOf(
            R.drawable.img_aquarium_1,
            R.drawable.img_aquariam_2,
            R.drawable.img_aquarium_3
        )
        binding.vpImage.adapter = bannerAdapter
        bannerAdapter.submitList(imageList)
        binding.indicator.setSliderWidth(resources.getDimension(R.dimen.indicator_width))
        binding.indicator.setSliderHeight(resources.getDimension(R.dimen.indicator_height))
        binding.indicator.setSlideMode(IndicatorSlideMode.SMOOTH)
        binding.indicator.setIndicatorStyle(IndicatorStyle.DASH)
        binding.indicator.setupWithViewPager(binding.vpImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}