package in.sunilpaulmathew.sCommon.Credits;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import in.sunilpaulmathew.sCommon.CommonUtils.sCommonUtils;
import in.sunilpaulmathew.sCommon.CommonUtils.sSerializableItems;

/*
 * Created by sunilpaulmathew <sunil.kde@gmail.com> on November 17, 2021
 */
public class sCreditsAdapter extends RecyclerView.Adapter<sCreditsAdapter.ViewHolder> {

    private static List<sSerializableItems> mData;
    private static int mAccentColor;

    public sCreditsAdapter(List<sSerializableItems> data, int color) {
        mData = data;
        mAccentColor = color;
    }

    @NonNull
    @Override
    public sCreditsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_credits, parent, false);
        return new sCreditsAdapter.ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull sCreditsAdapter.ViewHolder holder, int position) {
        holder.Title.setText(mData.get(position).getTextOne());
        holder.Description.setText(mData.get(position).getTextTwo());
        holder.Description.setPaintFlags(holder.Description.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        if (mAccentColor != Integer.MIN_VALUE) {
            holder.Description.setTextColor(mAccentColor);
        }
        holder.Description.setOnClickListener(v -> {
            if (mData.get(position).getTextThree() != null) {
                sCommonUtils.launchUrl(mData.get(position).getTextThree(), (Activity) v.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialTextView Title, Description;

        public ViewHolder(View view) {
            super(view);
            this.Title = view.findViewById(R.id.title);
            this.Description = view.findViewById(R.id.description);
        }
    }
}