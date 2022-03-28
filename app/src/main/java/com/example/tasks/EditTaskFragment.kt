package com.example.tasks
import android.R.string
import android.animation.ValueAnimator
import android.animation.ValueAnimator.ofFloat
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tasks.databinding.FragmentEditTaskBinding
import com.example.tasks.databinding.FragmentTasksBinding
import java.lang.reflect.Field


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditTaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding : FragmentEditTaskBinding?=null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("anir","OnCreateView ")
        // Inflate the layout for this fragment
        _binding = FragmentEditTaskBinding.inflate(inflater,container,false)
        val view = binding?.root

        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = EditTaskViewModelFactory(taskId ,dao)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(EditTaskViewModel::class.java)
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer {
            if(it){
                view?.findNavController()?.navigate(R.id.action_editTaskFragment_to_tasksFragment)
                viewModel.onNavigatedToList()
            }
        })

        return view
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val textView = view.findViewById<TextView>(R.id.taskId)
//        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId
//        textView.text = "This is a text that should be marqueeed.This is a text that should be marqueeed."
//        var flag = isMarqueed(textView.text.toString(),textView.width,textView)
//        if(flag){
//            Log.d("anir"," text Marqueed")
//        }
//        else
//            Log.d("anir","text as it is")

//        textView.isSelected = true

    }


    private fun isMarqueed(text: String, textWidth: Int, tv: TextView): Boolean {
        val testPaint = Paint()
        testPaint.set(tv.paint)
        var isMarquee = true
        if (textWidth > 0) {
            val availableWidth =
                (textWidth - tv.paddingLeft - tv.paddingRight - testPaint.measureText(text)) as Int
            println("...available width...$availableWidth")
            //        tv.setText(text);
            isMarquee = false
        }
        return isMarquee
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}