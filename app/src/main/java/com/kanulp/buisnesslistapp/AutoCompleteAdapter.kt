package com.kanulp.buisnesslistapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.LayoutRes
import com.kanulp.buisnesslistapp.model.Business
import com.kanulp.buisnesslistapp.model.BusinessSearchModel
import android.widget.TextView


class AutoCompleteAdapter(mContext: Context, @LayoutRes private val layoutResource: Int,
                          items:Array<Business>) : ArrayAdapter<Business>(mContext,layoutResource,items.toList()){


    var filteredMovies: List<Business> = listOf()

    override fun getCount(): Int = filteredMovies.size

    override fun getItem(position: Int): Business = filteredMovies[position]

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
        var text_title = view.findViewById(R.id.tv_title) as TextView

        text_title.text = filteredMovies[position].name

//        view.tv_title.text = filteredMovies[position].name
//        view.tvMovieYear.text = filteredMovies[position].year.toString()

        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                @Suppress("UNCHECKED_CAST")
                filteredMovies = filterResults.values as List<Business>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = FilterResults()
                filterResults.values = if (queryString == null || queryString.isEmpty())
                    items.asList()
                else
                    items.filter {
                        it.name.toLowerCase().contains(queryString) || it.year.toString().contains(queryString)
                    }
                return filterResults
            }
        }
    }


}
