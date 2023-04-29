package com.example.screensnc

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.screensnc.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRootBinding.bind(view)

        binding.openYellowBoxButton.setOnClickListener {
            openBox(Color.rgb(255, 255, 200),"Yellow")
        }

        binding.openGreenBoxButton.setOnClickListener {
            openBox(Color.rgb(200, 255, 200),"Green")
        }

        //  Для получения результата с возвращения со следующего экрана, при повороте экрана, результат придет еще раз, нужна проверка, способ так себе
        // findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("asd")?.observe(viewLifecycleOwner){}
        parentFragmentManager.setFragmentResultListener(
            BoxFragment.REQUEST_CODE,
            viewLifecycleOwner
        ) { _, data ->
            val number = data.getInt(BoxFragment.EXTRA_RANDOM_NUMBER)

            Toast.makeText(
                requireContext(), getString(R.string.generated_number, number), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun openBox(color: Int,colorName:String) {
        findNavController().navigate(
            R.id.action_rootFragment_to_boxFragment,
            bundleOf(BoxFragment.ARG_COLOR to color, BoxFragment.ARG_COLOR_NAME to colorName),
            navOptions {
                anim {
                    enter = R.anim.enter
                    exit = R.anim.exit
                    popEnter = R.anim.pop_enter
                    popExit = R.anim.pop_exit
                }
            }
        )
    }
}