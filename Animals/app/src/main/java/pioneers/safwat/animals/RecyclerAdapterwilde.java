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

            public class RecyclerAdapterwilde extends RecyclerView.Adapter<RecyclerAdapterwilde.ViewHolder>  {

                private String[] titles = {"Lion",
                        "Bear",
                        "Tiger",
                        "Cheetah",
                        "Elephant",
                        "Fox",
                        "Giraffe",
                        "Gorilla",
                        "Wolf",
                        "Zebra"};
                private int[] images = {R.drawable.king_of_beasts_lion_2,
                        R.drawable.bear_at_river_summer,
                        R.drawable.tiger_leaves_branches_background,
                        R.drawable.cheetah_and_grassland,
                        R.drawable.elephants_trunk,
                        R.drawable.wildlife_snow_fox,
                        R.drawable.giraffe_couple_love,
                        R.drawable.gorilla,
                        R.drawable.wolf,
                        R.drawable.zebra_foal_and_zebra};
                private int[] sounds = {R.raw.lion_e,
                        R.raw.bear_e,
                        R.raw.ttiger_e,
                        R.raw.cheta_e,
                        R.raw.elephant_e,
                        R.raw.fox_e,
                        R.raw.geraf_e,
                        R.raw.ghpreila_e,
                        R.raw.wolf_e,
                        R.raw.zebra_e };

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
                        (viewHolder.itemImage.getResources(), images[i], 150, 150));
            }

            @Override
            public int getItemCount() {
                return titles.length;
            }

            class ViewHolder extends RecyclerView.ViewHolder {

                public ImageView itemImage;
                public TextView itemTitle;
               // public  MediaPlayer mp;

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