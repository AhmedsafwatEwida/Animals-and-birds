package pioneers.safwat.animals;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdapterpetse extends RecyclerView.Adapter<RecyclerAdapterpetse.ViewHolder> {

    private String[] titles = {"Dog",
            "Donkey",
            "Cat",
            "Cheap",
            "Cow",
            "Goat",
            "Horse",
            "Rabbit"
    };
    private int[] images = {R.drawable.french_bulldog,
            R.drawable.donkey_of_shrek,
            R.drawable.baby_kitten_2,
            R.drawable.young_sheep,
            R.drawable.alpine_cow,
            R.drawable.two_goats_couple_on_the_hill,
            R.drawable.running_horse_in_water,
            R.drawable.white_rabbit};
    private int[] sounds = {R.raw.dog_e,
            R.raw.donkey_e,
            R.raw.cat_e,
            R.raw.cheap_e,
            R.raw.cow_e,
            R.raw.goat_e,
            R.raw.horse_e,
            R.raw.rabbit_e};

    private MediaPlayer mp;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
     //   viewHolder.itemDetail.setText(details[i]);
      //  viewHolder.itemImage.setImageResource(images[i]);
        viewHolder.itemImage.setImageBitmap( decodeSampledBitmapFromResource
                (viewHolder.itemImage.getResources(), images[i], 100, 100));
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImage;
        public TextView itemTitle;
    //    public  MediaPlayer mp;
      //  public TextView itemDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            itemImage =(ImageView)itemView.findViewById(R.id.item_image);
            itemTitle =(TextView)itemView.findViewById(R.id.item_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    stopPlaying();
                    mp = MediaPlayer.create(v.getContext(), sounds[position]);
                    mp.start();
                }
            });
        }
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    private void stopPlaying() {
        if (mp != null&&mp.isPlaying()) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}