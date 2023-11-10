package com.example.shemajamebeli_2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shemajamebeli_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var resultContainer: MutableList<MutableList<String>>
    private var stringRegex: Regex = Regex("^[a-zA-Z]+$")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultContainer = mutableListOf()

        save()
        groupElement()

    }


    private fun save(){
        binding.bSave.setOnClickListener {
            if(stringRegex.matches(binding.etText.text.toString())){
                compare(binding.etText.text.toString().lowercase())
                binding.etText.text.clear()
            }else{
                Toast.makeText(this, "Enter Only Characters", Toast.LENGTH_SHORT).show()
                binding.etText.text.clear()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun groupElement(){
        binding.apply {
            bOutput.setOnClickListener {
                tvResult.text = "Anagrams count ${resultContainer.size}"
            }
        }
    }

    private fun compare(str: String){
        val sortArr = sortString(str)
        for(i in resultContainer){
            for(j in i){
                if(sortString(j) == sortArr){
                    i.add(j)
                    return
                }
            }
        }
        val newList = mutableListOf(str)
        resultContainer.add(newList)
    }


    private fun sortString(str: String): String{
        return str.toCharArray().sorted().joinToString("")
    }


}