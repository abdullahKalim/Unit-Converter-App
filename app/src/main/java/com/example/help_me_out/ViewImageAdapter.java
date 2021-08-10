package com.example.help_me_out;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.Objects;

public class ViewImageAdapter extends PagerAdapter {
    Context context;
    int image[];
    LayoutInflater layoutInflater;
 public ViewImageAdapter(Context context,int image[])
 {
     this.context=context;
     this.image=image;
     layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(LinearLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView=layoutInflater.inflate(R.layout.image_item,container,false);
        ImageView imageView=itemView.findViewById(R.id.image);
        imageView.setImageResource(image[position]);
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
