package vobis.example.com.gamification.floatingactivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import vobis.example.com.gamification.R;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder> {

    private List<FeedItem> feedItemList;
    private Context context;

    public interface OnFloatingItemClickListener{
        public void onClick(FeedItem item);
    }

    private OnFloatingItemClickListener floatingListener;

    public OnFloatingItemClickListener getFloatingListener(){
        return floatingListener;
    }

    public void setFloatingListener(OnFloatingItemClickListener floatingListener){
        this.floatingListener = floatingListener;
    }

    public CustomRecyclerAdapter(Context context, List<FeedItem> feedItemList){
        this.feedItemList = feedItemList;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.floating_list_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        final FeedItem feedItem = feedItemList.get(i);
        if(!TextUtils.isEmpty(feedItem.getThumbnail())){
            System.out.println(feedItem.getThumbnail());
            Picasso.with(context).load(feedItem.getThumbnail())
                    .error(R.drawable.error)
                    .placeholder(R.drawable.icon)
                    .into(customViewHolder.imageView);
            customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingListener.onClick(feedItem);
            }
        };
        customViewHolder.textView.setOnClickListener(listener);
        customViewHolder.imageView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return (feedItemList != null ? feedItemList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        protected ImageView imageView;
        protected TextView textView;

        public CustomViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.textView = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
