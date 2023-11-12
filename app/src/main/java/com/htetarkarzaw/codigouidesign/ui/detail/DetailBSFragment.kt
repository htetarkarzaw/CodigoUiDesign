package com.htetarkarzaw.codigouidesign.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.htetarkarzaw.codigouidesign.R
import com.htetarkarzaw.codigouidesign.databinding.FragmentBsDetailBinding
import com.htetarkarzaw.codigouidesign.ui.home.BannerAdapter
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle

class DetailBSFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentBsDetailBinding? = null
    private val binding get() = _binding!!
    private val bannerAdapter by lazy {
        BannerAdapter {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        dialog?.setOnShowListener { dialog ->
            val bottomSheet =
                (dialog as BottomSheetDialog).findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            val behavior = BottomSheetBehavior.from(bottomSheet!!)
            val screenHeight = resources.displayMetrics.heightPixels
            val peekHeight =
                (screenHeight * 1).toInt() // Set peek height to 90% of the screen height
            behavior.peekHeight = peekHeight
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        setUpViewPager()
        binding.ivClose.setOnClickListener {
            dismiss()
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