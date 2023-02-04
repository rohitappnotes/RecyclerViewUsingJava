package com.recyclerview.using.java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.recyclerview.using.java.R;
import com.recyclerview.using.java.model.Product;
import java.util.ArrayList;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ItemViewHolder> {

    private final Context context;
    private ArrayList<Product> items;
    private ItemClickListener itemClickListener;
    private ItemLongClickListener itemLongClickListener;

    public ProductRecyclerViewAdapter(Context context, ArrayList<Product> items) {
        this.context = context;
        this.items = items;
    }

    public void add(int position, Product product) {
        if (items == null) {
            items = new ArrayList<Product>();
        }
        items.add(position, product);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        if (items.size() > 0) {
            items.remove(position);
            this.notifyItemRemoved(position);
        }
    }

    public void clear() {
        int size = items.size();
        items.clear();
        this.notifyItemRangeRemoved(0, size);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        Product item = items.get(position);
        itemViewHolder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final ImageView productImageView;
        private final TextView productNameTextView;
        private final TextView productPriceTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
            productPriceTextView = itemView.findViewById(R.id.productPriceTextView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (itemClickListener != null && position != RecyclerView.NO_POSITION) {
                itemClickListener.onClick(position, view, items.get(position));
            }
        }

        @Override
        public boolean onLongClick(View view) {
            int position = getAdapterPosition();
            if (itemLongClickListener != null && position != RecyclerView.NO_POSITION) {
                itemLongClickListener.onLongClick(position, view, items.get(position));
                return true;
            }
            return false;
        }

        public void bindItem(Product item) {
            productNameTextView.setText(item.getName());
            productPriceTextView.setText("\u20B9 " + item.getPrice());
        }
    }

    public interface ItemClickListener {
        void onClick(int position, View view, Product item);
    }

    public interface ItemLongClickListener {
        void onLongClick(int position, View view, Product item);
    }

    public void setItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setItemLongClickListener(ItemLongClickListener listener) {
        this.itemLongClickListener = listener;
    }
}