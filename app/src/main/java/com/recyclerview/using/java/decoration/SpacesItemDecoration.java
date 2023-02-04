package com.recyclerview.using.java.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

/*
 SpacesItemDecoration is a {@link RecyclerView.ItemDecoration} that can be
 used to add spacing between items of a {@link LinearLayoutManager}. It
 supports both {@link #HORIZONTAL} and {@link #VERTICAL} orientations.

 LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
 recyclerView.setLayoutManager(layoutManager);

 SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration( 16, layoutManager.getOrientation());
 recyclerView.addItemDecoration(spacesItemDecoration);

 productRecyclerViewAdapter = new ProductRecyclerViewAdapter(getApplicationContext(), productArrayList);
 recyclerView.setAdapter(productRecyclerViewAdapter);
*/
public final class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public static final int VERTICAL = OrientationHelper.VERTICAL;

    private final int space;

    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int orientation;

    /**
     * Sets the orientation for this divider. This should be called if
     * {@link RecyclerView.LayoutManager} changes orientation.
     *
     * @param space       Item spacing in PX
     * @param orientation {@link #HORIZONTAL} or {@link #VERTICAL}
     */
    public SpacesItemDecoration(final int space, final int orientation) {
        this.space = space;
        setOrientation(orientation);
    }

    /**
     * Sets the orientation for this divider. This should be called if
     * {@link RecyclerView.LayoutManager} changes orientation.
     *
     * @param orientation {@link #HORIZONTAL} or {@link #VERTICAL}
     */
    public void setOrientation(final int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
       /* if (orientation == HORIZONTAL) {
            outRect.left = space;
            outRect.right = space;
            outRect.top = 0;
            outRect.bottom = 0;
        } else {
            outRect.left = 0;
            outRect.right = 0;
            outRect.bottom = space;
            outRect.top = space;
        }*/

        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}