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

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {

    private String[] titles = {"بجعة",
            "ببغاء",
            "بطريق",
            "دجاجة",
            "ديك",
            "حمامة",
            "نسر",
            "نورس"
            };
    private int[] images = {R.drawable.pelican_bird_on_hippo,
            R.drawable.macaw,
            R.drawable.penguins_water,
            R.drawable.chicken,
            R.drawable.rooster,
            R.drawable.cute_white_dove,
            R.drawable.bald_eagle_2,
            R.drawable.seagulls_flying_in_the_sky
           };
    private int[] sounds = {R.raw.bagae_a,
            R.raw.bbabbaga_a,
            R.raw.betrek_a,
            R.raw.dagaga_a,
            R.raw.dek_a,
            R.raw.hamama_a,
            R.raw.nesr_a,
            R.raw.noras_a
          };

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
      //  public  MediaPlayer mp;
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