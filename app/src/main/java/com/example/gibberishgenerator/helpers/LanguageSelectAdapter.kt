package com.example.gibberishgenerator.helpers

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gibberishgenerator.GeneratorActivity
import com.example.gibberishgenerator.LanguageSelectActivity
import com.example.gibberishgenerator.R
import com.example.gibberishgenerator.languageobjects.Language


class LanguageSelectAdapter(private val wrapperObject : LanguageSelectWrapper) :
    RecyclerView.Adapter<LanguageSelectAdapter.ViewHolder>() //what is up with viewholder
{
    val TAG = "LanguageSelectAdapter"


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.language_select_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return wrapperObject.count
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "SpellsAdapter  List Count: ${wrapperObject.count}")

        //input is Spell object
        return holder.bind(wrapperObject.results[position])
    }


    //Establishes and assigns the data to the display elements
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val TAG = "ViewholdLangAdapt"


        var languageNameTextView: TextView =
            itemView.findViewById<TextView>(R.id.textView_languageSelectFragment_name)
        var languageDropdownButton : ImageButton =
            itemView.findViewById(R.id.button_languageSelectFragment_dropdown)

        fun bind(lang: Language) {

            languageNameTextView.text = lang.name
            Log.d(TAG, "language name: ${lang.name}")
            languageNameTextView.setOnClickListener {
                Log.d(TAG, "language clicked.")

                //the stuff that gets done when a language is clicked
                val specificSpellIntent = Intent(itemView.context, GeneratorActivity::class.java)
                //specificSpellIntent.putExtra(GeneratorActivity.EXTRA_SPELL_INDEX, spell.index)
                itemView.context.startActivity(specificSpellIntent)

            }

            //TODO come back to this later
            languageDropdownButton.setOnClickListener {
                Log.d(TAG, "dropdown clicked.")
//
//                val menu = PopupMenu(itemView.context, itemView)
//
//                menu.menu.add("Edit").setOnMenuItemClickListener {
//
//                }
//                menu.menu.add("Delete")
//                menu.show()

            }

        }
    }
}