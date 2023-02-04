package com.recyclerview.using.java.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
  GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
  recyclerView.setLayoutManager(gridLayoutManager);
  recyclerView.addItemDecoration(new GridSpacingDecoration(10, 100, 50, 200, 5));
  productRecyclerViewAdapter = new ProductRecyclerViewAdapter(getApplicationContext(), productArrayList);
  recyclerView.setAdapter(productRecyclerViewAdapter);
*/
public class GridSpacingDecoration extends RecyclerView.ItemDecoration {

    private final int left;
    private final int right;
    private final int top;
    private final int bottom;
    private final int middle;
    private int[] horizontalSpaces;

    public GridSpacingDecoration(int top, int right, int bottom, int left, int middle) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
        this.middle = middle;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        if (layoutManager == null) {
            return;
        }
        int spanCount = getSpanCount(layoutManager);
        int position = parent.getChildAdapterPosition(view); // item position
        int column = layoutManager.getSpanSizeLookup().getSpanIndex(position, spanCount);
        int row = layoutManager.getSpanSizeLookup().getSpanGroupIndex(position, spanCount);

        if (horizontalSpaces == null) {
            int recyclerWith = parent.getWidth();
            horizontalSpaces = initHorizontalSpaces(spanCount, recyclerWith, left, right, middle);
        }
        outRect.left = horizontalSpaces[column * 2];
        outRect.right = horizontalSpaces[column * 2 + 1];
        if (!isFirstRow(row)) {
            outRect.top = middle;
        }
        if (isFirstRow(row)) {
            outRect.top = top;
        }
        if (isLastRow(parent, row)) {
            outRect.bottom = bottom;
        }
    }

    private int getSpanCount(GridLayoutManager layoutManager) {
        return layoutManager.getSpanCount();
    }

    private boolean isFirstRow(int row) {
        return row == 0;
    }

    private boolean isLastRow(RecyclerView recyclerView, int row) {
        return row == getLastRow(recyclerView);
    }

    private int getLastRow(RecyclerView recyclerView) {
        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        assert layoutManager != null;
        int spanCount = layoutManager.getSpanCount();
        return layoutManager.getSpanSizeLookup().getSpanGroupIndex(layoutManager.getItemCount() - 1, spanCount);
    }

    private int[] initHorizontalSpaces(int spanCount, int recyclerWidth, int start, int end, int middle) {
        int[] horizontalSpaces = new int[spanCount * 2];
        int itemWidthFull = recyclerWidth / spanCount;
        int itemWidthAfterAddSpacing = (recyclerWidth - start - end - middle * (spanCount - 1)) / spanCount;

        int i = 0;
        while (i < horizontalSpaces.length) {
            if (i == 0) {
                horizontalSpaces[i] = start;
            } else {
                horizontalSpaces[i] = middle - horizontalSpaces[i - 1];
            }
            horizontalSpaces[i + 1] =
                    itemWidthFull - itemWidthAfterAddSpacing - horizontalSpaces[i];
            i += 2;
        }
        return horizontalSpaces;
    }
}
