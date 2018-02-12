package manddev.com.testavito.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import manddev.com.testavito.R;
import manddev.com.testavito.databinding.ItemGhibliBinding;
import manddev.com.testavito.viewmodel.GhibliViewModel;

/**
 * @author Metrozal on 12/02/2018.
 */

public class GhibliAdapter extends RecyclerView.Adapter<GhibliAdapter.ViewHolder> {

    private final Listener mListener;
    private final ArrayList<GhibliViewModel> mData;

    public GhibliAdapter(final Listener listener) {
        this.mListener = listener;
        mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGhibliBinding binding = ItemGhibliBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(ArrayList<GhibliViewModel> data) {
        this.mData.clear();
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ItemGhibliBinding mBinding;
        private GhibliViewModel mModel;

        // Don't have time to think of another way using Picasso and Databinding
        private ImageView mRandomPictureView;

        ViewHolder(ItemGhibliBinding binding) {
            super(binding.getRoot());
            final View root = binding.getRoot();
            final Context context = root.getContext();

            root.setOnClickListener(this);

            mBinding = binding;

            // Sorry
            mRandomPictureView = root.findViewById(R.id.ghibli_image);
            String url = context.getString(R.string.random_picture_api);
            Picasso.with(context)
                    .invalidate(url);
            Picasso.with(context)
                    .load(url)
                    .networkPolicy(NetworkPolicy.NO_CACHE).
                    memoryPolicy(MemoryPolicy.NO_CACHE)
                    .resize(200, 200)
                    .centerCrop()
                    .into(mRandomPictureView);
        }

        void setData(GhibliViewModel model) {
            mModel = model;
            mBinding.setModel(mModel);
            mBinding.executePendingBindings();

            // Accessibility
            final Context context = mRandomPictureView.getContext(); // No context is retained
            mRandomPictureView.setContentDescription(context.getString(R.string.content_description, mModel.getTitle(context), mModel.getScore(context)));
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(mModel);
        }
    }

    public interface Listener {
        void onItemClick(GhibliViewModel model);
    }
}
