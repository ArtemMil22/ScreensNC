package com.example.screensnc

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.screensnc.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBoxBinding.bind(view)

        //val color = requireArguments().getInt(ARG_COLOR)
        // Cпособ 1 val color = BoxFragmentArgs.fromBundle(requireArguments()).color
        val color = args.color

        binding.root.setBackgroundColor(color)

        binding.goBackButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.openSecretButton.setOnClickListener {
            findNavController().navigate(BoxFragmentDirections.actionBoxFragmentToSecretFragment())
        }

        binding.generateNumberButton.setOnClickListener {
            val number = Random.nextInt(100)
            // способ отправки аргумента на предыдущий экран на 21 год
            // findNavController().previousBackStackEntry?.savedStateHandle?.set("asd",number)
            parentFragmentManager.setFragmentResult(
                REQUEST_CODE,
                bundleOf(EXTRA_RANDOM_NUMBER to number)
            )
            findNavController().popBackStack()
        }
    }

    companion object {

        // используя FragmentResultApi (FRA) по следующему ключу мы сможем
        // получить результат
        const val REQUEST_CODE = "RANDOM_NUMBER_REQUEST_CODE"

        // под нижеследующем будет ключ самого значения сгенерированного number
        const val EXTRA_RANDOM_NUMBER = "EXTRA_RANDOM_NUMBER"
    }
}