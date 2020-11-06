package com.kks.techflittertestkirit.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.kks.techflittertestkirit.R
import com.kks.techflittertestkirit.listener.OnRecyclerItemClick
import com.kks.techflittertestkirit.model.UserItem
import kotlinx.android.synthetic.main.item_row_user.view.*

/**
 * Adapter class for User
 *
 * @author : Kirit Khant
 * @since : 05-11-2020.
 */

class UserAdapter(
    private val dataList: ArrayList<UserItem>,
    private val mContext: Context,
    private val onItemClickListener: OnRecyclerItemClick
) : RecyclerView.Adapter<UserAdapter.FileHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.item_row_user, parent, false)
        return FileHolder(v)
    }

    override fun onBindViewHolder(holder: FileHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data, mContext, dataList, position, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class FileHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvName = itemView.tvName
        private val tvEmail = itemView.tvEmail
        private val ivCheck = itemView.ivCheck
        private val clUser = itemView.clUser
        private val clUserr = itemView.clUserr
        private val btnDelete = itemView.btnDelete


        fun bind(
            data : UserItem,
            context: Context, dataList: ArrayList<UserItem>,
            position: Int,
            onItemClickListener: OnRecyclerItemClick
        ) {

            tvName.text = "Name : ${data.name}"
            tvEmail.text = "Email : ${data.email}"

            if (data.isClicked) {
                ivCheck.visibility = View.VISIBLE
            } else {
                ivCheck.visibility = View.GONE
            }

            clUser.setOnClickListener {
                Log.e("Click",  "$adapterPosition clicked")
                onItemClickListener.onRecyclerItemClick(clUser, adapterPosition, "")
            }

            btnDelete.setOnClickListener {
                Log.e("Click",  "$adapterPosition clicked")
                onItemClickListener.onRecyclerItemClick(btnDelete, adapterPosition, "")
            }
        }
    }
}