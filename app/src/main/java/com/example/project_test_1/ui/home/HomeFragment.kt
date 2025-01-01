package com.example.project_test_1.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project_test_1.R
import com.example.project_test_1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding // No non-null assertion here

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding?.root ?: return null // Safely access the binding and return root view

        // Observe LiveData for lecture details
        homeViewModel.lectureName.observe(viewLifecycleOwner) { lectureName ->
            binding?.lectureName?.text = getString(R.string.lecture_name, lectureName)
        }

        homeViewModel.lectureHall.observe(viewLifecycleOwner) { lectureHall ->
            binding?.lectureHallName?.text = getString(R.string.lecture_hall, lectureHall)
        }

        homeViewModel.remainingTime.observe(viewLifecycleOwner) { remainingTime ->
            binding?.remainingTimeLabel?.text = getString(R.string.remaining_time, remainingTime)
        }

        // Countdown Timer
        homeViewModel.countdownTime.observe(viewLifecycleOwner) { countdownMillis ->
            startCountdownTimer(countdownMillis)
        }

        return root
    }

    private fun startCountdownTimer(timeInMillis: Long) {
        object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val hours = millisUntilFinished / (1000 * 60 * 60)
                val minutes = (millisUntilFinished / (1000 * 60)) % 60
                val seconds = (millisUntilFinished / 1000) % 60
                binding?.countdownTimer?.text = getString(R.string.countdown_timer, String.format("%02d:%02d:%02d", hours, minutes, seconds))
            }

            override fun onFinish() {
                binding?.countdownTimer?.text = getString(R.string.countdown_timer, "Time's up!")
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
