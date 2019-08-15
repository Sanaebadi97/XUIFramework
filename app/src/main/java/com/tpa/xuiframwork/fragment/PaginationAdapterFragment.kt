package com.tpa.xuiframwork.fragment

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tpa.xuiframework.adapter.PaginationAdapter
import com.tpa.xuiframwork.R
import com.tpa.xuiframwork.entity.TestData

class PaginationAdapterFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pagination_adapter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val pAdapter = PaginationAdapter<TestData>(
            R.layout.row_list_test_binding,
            R.layout.row_loading,
            recyclerView,
            { adapter, lastIndex ->

                //on reached last item do network request
                Handler().postDelayed({
                    adapter.addItem(getList())
                }, 2000)
            }
        )

        recyclerView.adapter = pAdapter
        pAdapter.addItem(getList())
    }

    private fun getList(): List<TestData> = arrayListOf(
        TestData("item 1", "https://pickaface.net/gallery/avatar/Benjohnsone54fbec7a167c5.png"),
        TestData("item 2"),
        TestData("item 3"),
        TestData("item 4"),
        TestData("item 5"),
        TestData("item 6"),
        TestData("item 7"),
        TestData("item 8"),
        TestData("item 9"),
        TestData("item 10"),
        TestData("item 11"),
        TestData("item 12"),
        TestData("item 13")
    )
}