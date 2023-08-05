package com.example.marstest.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.marstest.Model.Remoto.TerrenoDeMArte
import com.example.marstest.R
import com.example.marstest.ViewModel.MarsViewModel
import com.example.marstest.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MarsViewModel by activityViewModels()
    private var terrenoSeleccionado: TerrenoDeMArte?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


        viewModel.selectedItem().observe(viewLifecycleOwner){
            it?.let {
                seleccion->
                Glide.with(binding.imageView2).load(it.img_src).centerCrop().into(binding.imageView2)
                binding.textView.setText(seleccion.id)
                binding.textView2.setText(seleccion.type)
                binding.textView3.setText(seleccion.price.toString())
                terrenoSeleccionado=seleccion
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}