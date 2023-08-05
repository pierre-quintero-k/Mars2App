package com.example.marstest.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marstest.R
import com.example.marstest.ViewModel.MarsAdapter
import com.example.marstest.ViewModel.MarsViewModel
import com.example.marstest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : MarsViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
       /* //Observamos todoMarte y a eso le permitimos que el campo de texto sea llenado con eso
       esta era la forma de llamar directo al textview

        viewModel.todoMarte.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textviewFirst.text=it.toString()
            }
        })*/

        /**Implementando el adapter y RV*/
        val adapter=MarsAdapter()
        binding.rv1.adapter=adapter
        //el layout sera de tipo grid con 2 elementos
        binding.rv1.layoutManager=GridLayoutManager(context,2)
        binding.rv1.addItemDecoration(
            DividerItemDecoration(context,
            DividerItemDecoration.HORIZONTAL)
        )

        viewModel.todoMarte.observe(viewLifecycleOwner, Observer {

            adapter.updateData(it)

            //captura bien el elemento seleccionado
            })

        //primera pureba para enviar el elemento
        adapter.terrenoSeleccionado.observe(viewLifecycleOwner){
            it?.let {
                viewModel.selected(it)
                Log.d("*****ENVIANDO****", it.toString())
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}