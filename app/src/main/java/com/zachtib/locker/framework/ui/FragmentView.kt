package com.zachtib.locker.framework.ui

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class FragmentView(
    @LayoutRes private val layoutId: Int,
    @MenuRes private val menuId: Int? = null
) : Fragment(), CoroutineScope {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        setHasOptionsMenu(menuId != null)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (menuId != null) {
            inflater?.inflate(menuId, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    protected fun View.onClick(listener: suspend () -> Unit) {
        this.setOnClickListener {
            launch {
                listener()
            }
        }
    }
}