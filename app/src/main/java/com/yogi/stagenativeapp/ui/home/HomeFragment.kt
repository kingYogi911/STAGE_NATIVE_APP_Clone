package com.yogi.stagenativeapp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ScrollState
import com.yogi.stagenativeapp.R
import com.yogi.stagenativeapp.databinding.FragmentHomeBinding
import com.yogi.stagenativeapp.ui.home.data.HomeScrollItem
import com.yogi.stagenativeapp.ui.movieAdapters.MovieAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val infiniteRecyclerAdapter = InfiniteRecyclerAdapter()

    private val adapter1 = MovieAdapter()
    private val adapter2 = MovieAdapter()
    private val adapter3 = MovieAdapter()
    private val adapter4 = MovieAdapter()
    private val specialForYouAdapter = MovieAdapter()
    private val adapter5 = MovieAdapter()
    private val adapter6 = MovieAdapter()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.apply {
            swipeRefresh.setColorSchemeResources(R.color.red)
            swipeRefresh.setProgressBackgroundColorSchemeResource(R.color.black_window_background)
            swipeRefresh.setOnRefreshListener {
                viewLifecycleOwner.lifecycleScope.launch {
                    delay(100)
                    swipeRefresh.isRefreshing = false
                }
            }
            viewPager.adapter = infiniteRecyclerAdapter
            viewPager.currentItem = 1
            viewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    val listSize = viewModel.data.value.size
                    if (state == ViewPager2.SCROLL_STATE_IDLE) {
                        when (viewPager.currentItem) {
                            listSize + 1 -> viewPager.setCurrentItem(1, false)
                            0 -> viewPager.setCurrentItem(listSize - 1, false)
                        }
                    }
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val listSize = viewModel.data.value.size
                    if (position != 0 && position != listSize + 1) {
                        tabIndicator.getTabAt(position - 1)?.select()
                    }
                }
            })
            moviesList1.also {
                it.rv.adapter = adapter1
                it.tvTitle.text = "Top 20 in Haryana"
                it.tvTitle.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                it.btSeeAll.isVisible = false
            }
            moviesList2.also {
                it.rv.adapter = adapter2
                it.tvTitle.text = "VIP Shows"
                it.btSeeAll.setOnClickListener {
                    //TODO()
                }
            }
            moviesList3.also {
                it.rv.adapter = adapter3
                it.tvTitle.text = "VIP Videos"
                it.btSeeAll.setOnClickListener {
                    //TODO()
                }
            }
            moviesList4.also {
                it.rv.adapter = adapter4
                it.tvTitle.text = "Coming Soon"
                it.tvTitle.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
            }
            moviesList5.also {
                it.rv.adapter = specialForYouAdapter
                it.tvTitle.text = "Special For You"
                it.tvTitle.setTextColor("#ffffff".toColorInt())
            }
            genreLayout.apply {
                webSeriesList.also {
                    it.rv.adapter = adapter5
                    it.tvTitle.text = "Web Series"
                    it.btSeeAll.setOnClickListener {

                    }
                }
                videosList.also {
                    it.rv.adapter = adapter6
                    it.tvTitle.text = "Videos"
                    it.tvTitle.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                    it.btSeeAll.setOnClickListener {

                    }
                }
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //start scrolling
        viewLifecycleOwner.lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                while (true) {
                    delay(8000)
                    withContext(Dispatchers.Main) {
                        if(infiniteRecyclerAdapter.itemCount>0) {
                            binding.viewPager.run {
                                if(this.scrollState == ViewPager2.SCROLL_STATE_IDLE) {
                                    setCurrentItem(
                                        binding.viewPager.currentItem + 1,
                                        true
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.data.collect {
                    updateData(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.vipMovies.collectLatest {
                    adapter1.setData(it)
                    adapter2.setData(it)
                    adapter3.setData(it)
                    adapter4.setData(it)
                    specialForYouAdapter.setData(it)
                    adapter5.setData(it)
                    adapter6.setData(it)
                }
            }
        }
    }

    private fun updateData(data: List<HomeScrollItem>) {
        infiniteRecyclerAdapter.setData(data)
        binding.tabIndicator.removeAllTabs()
        repeat(data.size) {
            binding.tabIndicator.apply {
                val tab = newTab()
                this.addTab(tab)
            }
        }
        binding.viewPager.currentItem = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}