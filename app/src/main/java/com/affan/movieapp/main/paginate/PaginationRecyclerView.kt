package com.affan.movieapp.main.paginate

import android.util.Log
import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationRecyclerView (
    private val loadMore : () -> Unit
) : RecyclerView.OnScrollListener() {

    private var isLastPage = false

    private var isLoading = false

    private var isScrolling = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as GridLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val mChildCount = layoutManager.childCount
        val mItemCount = layoutManager.itemCount
        val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
        val isLastItem = firstVisibleItemPosition + mChildCount >= mItemCount * 0.5
        val isNotBeginning = firstVisibleItemPosition >= 0
        val isTotalMoreThanVisible = mItemCount >= 10
        val shouldPaginate = isNotLoadingAndNotLastPage && isLastItem &&
                isNotBeginning && isTotalMoreThanVisible && isScrolling

        if (shouldPaginate){
            loadMore()
            isScrolling = false
            Log.e("Pagination", mItemCount.toString())
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
            isScrolling = true
        }
    }

}
