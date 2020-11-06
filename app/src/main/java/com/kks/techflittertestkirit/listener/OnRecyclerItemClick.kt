package com.kks.techflittertestkirit.listener

import android.view.View

interface OnRecyclerItemClick {
    fun onRecyclerItemClick(view: View, position: Int, type: String = "")
}

