package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{
    private List<Tweet> mTweets;
    Context context;
    //pass in  the Tweets array in the constructor
    public TweetAdapter(List<Tweet> tweets) {
        mTweets = tweets;
    }
    // for each row, inflate the layout and cache refrenrence into Viewholder

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_tweet,parent,false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }
    // create ViewHolder class


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get data according to position
        Tweet tweet = mTweets.get(position);
        String time = getRelativeTimeAgo(tweet.createdAt);
        // populate the views according to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        holder.tvTime.setText(time);
        holder.tvLikes.setText(tweet.likes);
        holder.tvRetweets.setText(tweet.retweets);

        //holder.tvReplies.setText(tweet.replies);

        Glide.with(context)
                .load(tweet.user.profileImageUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;
        public TextView tvTime;
        public TextView tvLikes;
        public TextView tvRetweets;
        public String mediaUrl;
        //public TextView tvReplies;

        public ViewHolder(View itemView) {
            super(itemView);

            // perform findViewById lookups

            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsertName);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
            tvRetweets = (TextView) itemView.findViewById(R.id.tvRetweets);
            //tvReplies = (TextView) itemView.findViewById(R.id.tvReplies);
        }

    }
    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }
}
