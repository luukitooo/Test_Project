package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PreferenceItemBinding
import com.example.myapplication.databinding.PreferenceItemSwitchBinding

class PreferenceAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onDeleteClickListener: ((Preference) -> Unit)? = null

    private var preferenceList = listOf<Preference>()

    inner class StandardPreference(val binding: PreferenceItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val preference = preferenceList[adapterPosition]
            binding.apply {
                preferenceTitle.text = preference.title
                deleteButton.setOnClickListener {
                    onDeleteClickListener?.invoke(preference)
                }
            }
        }
    }

    inner class SwitchdPreference(val binding: PreferenceItemSwitchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val preference = preferenceList[adapterPosition]
            binding.apply {
                preferenceTitle.text = preference.title
                deleteButton.setOnClickListener {
                    onDeleteClickListener?.invoke(preference)
                }
            }
        }
    }

    fun setData(newList: List<Preference>) {
        val callback = PreferenceDiffCallback(preferenceList, newList)
        val result = DiffUtil.calculateDiff(callback)
        preferenceList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewTypes.SWITCH_PREFERENCE -> SwitchdPreference(PreferenceItemSwitchBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
            else -> StandardPreference(PreferenceItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StandardPreference -> holder.bind()
            is SwitchdPreference -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val preference = preferenceList[position]
        return when (preference.isSwitch) {
            true -> ViewTypes.SWITCH_PREFERENCE
            else -> ViewTypes.STANDARD_PREFERENCE
        }
    }

    override fun getItemCount() = preferenceList.size

    inner class PreferenceDiffCallback(
        private val oldList: List<Preference>,
        private val newList: List<Preference>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == newList[newItemPosition].title
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

    }

}