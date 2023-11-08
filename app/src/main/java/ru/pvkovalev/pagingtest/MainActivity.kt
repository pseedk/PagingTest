package ru.pvkovalev.pagingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.pvkovalev.pagingtest.adapter.RickMortyPagedAdapter
import ru.pvkovalev.pagingtest.databinding.ActivityMainBinding
import ru.pvkovalev.pagingtest.viewmodel.RickMortyViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mAdapter by lazy {
        RickMortyPagedAdapter()
    }
    private val viewModel: RickMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRv()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRv() {
        binding.recyclerView.apply {
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}