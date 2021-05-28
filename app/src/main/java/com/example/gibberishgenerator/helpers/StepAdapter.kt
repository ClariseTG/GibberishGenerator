package com.example.gibberishgenerator.helpers

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.gibberishgenerator.GeneratorActivity
import com.example.gibberishgenerator.R
import com.example.gibberishgenerator.languageobjects.Language
import com.example.gibberishgenerator.R.layout.activity_language_edit

class StepAdapter(private val wrapperObject : LanguageSelectWrapper) :
    RecyclerView.Adapter<StepAdapter.ViewHolder>() //what is up with viewholder
{
    //var itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
    //itemTouchHelper.attachToRecyclerView(recycler_edit_steps)


    val itemTouchHelperCallback = object : ItemTouchHelper.Callback()  {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            // Specify the directions of movement
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            return makeMovementFlags(dragFlags, 0)
        }


       override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            // Notify your adapter that an item is moved from x position to y position
            recyclerView.adapter?.notifyItemMoved(viewHolder.adapterPosition, target.adapterPosition)
            return true
        }

        override fun isLongPressDragEnabled(): Boolean {
            // true: if you want to start dragging on long press
            // false: if you want to handle it yourself
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }

        override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
            super.onSelectedChanged(viewHolder, actionState)
            // Hanlde action state changes
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            // Called by the ItemTouchHelper when the user interaction with an element is over and it also completed its animation
            // This is a good place to send update to your backend about changes
        }


    }




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

            }

        }
    }


}