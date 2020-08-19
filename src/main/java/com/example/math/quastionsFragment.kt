package com.example.math

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_quastions.view.*


class quastionsFragment : Fragment() {
    data class Question(
        var question: String,
        var answers: MutableList<String>
    )

    lateinit var btn1: RadioButton;
    lateinit var btn2: RadioButton;
    lateinit var btn3: RadioButton;
    lateinit var btn4: RadioButton;
    var score: Int = 0;
    var count: Int = 0;
    var correctAnswer: String = "";
    var questions: MutableList<Question> = mutableListOf(
        Question(
            "What is the capital city of Australia?",
            mutableListOf("Canberra", "Sydney", "Brisbane", "Melbourne")
        ),
        Question(
            "Which US state was Donald Trump born in?",
            mutableListOf("New York", "Texas", "Maine", "Washington")
        ),
        Question(
            "Which UK city is situated further west – Bristol or Edinburgh?",
            mutableListOf("Edinburgh", "London", "Manchester", "Melbourne")
        ),
        Question(
            "How many countries are there in the region of Europe?",
            mutableListOf("44", "45", "32", "50")
        ),
        Question(
            "What is the capital of Finland?",
            mutableListOf("Helsinki", "Espoo", "Tampere", "Vantaa")
        ),
        Question(
            "What language is spoken in Brazil?",
            mutableListOf("Portuguese", "English", "Brazilian", "German")
        ),
        Question(
            "How many permanent members are there on the UN security council?",
            mutableListOf("5", "12", "6", "3")
        )
    )
    var tempQuestions = questions;


    fun randomizeQuestions() {
        tempQuestions.shuffle()
        count++
    }

    fun setQuestions(view: View) {

        var tempAnswers = tempQuestions[0].answers;
        view.questionText.text = tempQuestions[0].question;
        correctAnswer = tempQuestions[0].answers[0]
        tempAnswers.shuffle()
        btn1 = view.findViewById(R.id.radioButton1);
        btn2 = view.findViewById(R.id.radioButton2);
        btn3 = view.findViewById(R.id.radioButton3);
        btn4 = view.findViewById(R.id.radioButton4);
        btn1.text = tempAnswers[0];
        btn2.text = tempAnswers[1];
        btn3.text = tempAnswers[2];
        btn4.text = tempAnswers[3];

        view.quastionNumber.text = "Question number " + count.toString()
        tempQuestions.removeAt(0);
    }

    lateinit var radioGroup: RadioGroup;
    lateinit var radioButton: RadioButton;
    lateinit var nextButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tempQuestions = questions;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quastions, container, false);
        var fl:Boolean = false



        nextButton = view.findViewById(R.id.nextButton);
        radioGroup = view.findViewById(R.id.radioGroup);
        nextButton.isEnabled = false
        var temp: Long = -1;

        //Set questions and answers
        randomizeQuestions();
        setQuestions(view)

        //Set RadioGroup oncLickListener

        radioGroup.setOnCheckedChangeListener{ radioGroup, i ->
            radioButton = view.findViewById(i);

            if(!fl) {
                radioButton.isChecked = true
                fl = true
                nextButton.isEnabled = true
            }

        }

        //Set onClickListener for the next button.
            nextButton.setOnClickListener {
//            //println(radioButton.isChecked)
            nextButton.isEnabled = false
            radioButton.isChecked = false
            fl = false
            for (i in 0..3) {

                if (radioButton.text == correctAnswer) {
                    score++
                    break
                }
            }
//            btn1.isChecked = false;
//            btn2.isChecked = false;
//            btn3.isChecked = false;
//            btn4.isChecked = false;
//
            view.Score.text = "Score: $score"
            if (tempQuestions.size != 0) {
                randomizeQuestions()
                setQuestions(view)
            } else {
                if (score < count - count * 0.25) findNavController().navigate(
                    quastionsFragmentDirections.actionQuastionsFragmentToLoserFragment(score)
                )
                else findNavController().navigate(
                    quastionsFragmentDirections.actionQuastionsFragmentToWinnerFragment(score)
                )
            }

        }
        return view;
    }
}

