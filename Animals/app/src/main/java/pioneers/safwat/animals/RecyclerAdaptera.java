package pioneers.safwat.animals;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerAdaptera extends RecyclerView.Adapter<RecyclerAdaptera.ViewHolder> {
Context context;
    private String[] titles = {"الحيوانات البرية",
            "الحيوانات الأليفة",
            "الطيور"
          };

    private int[] images = {R.drawable.wild_animals,
            R.drawable.chainimage_animals_pictures,
            R.drawable.flightless_birds};

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
       // viewHolder.itemImage.setImageResource(images[i]);
     viewHolder.itemImage.setImageBitmap( decodeSampledBitmapFromResource
                (viewHolder.itemImage.getResources(), images[i], 200, 200));
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemTitle;
      //  public TextView itemDetail;
        public ViewHolder(View itemView) {
            super(itemView);
            itemImage =(ImageView)itemView.findViewById(R.id.item_image);
            itemTitle =(TextView)itemView.findViewById(R.id.item_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    switch (position) {
                        case 0:
                            Intent i = new Intent(v.getContext(),CardDemoWilda.class);
                            v.getContext().startActivity(i);
                            break;
                        case 1:
                            Intent i2 = new Intent(v.getContext(),CardDemopitsa.class);
                            v.getContext().startActivity(i2);
                            break;
                        case 2:
                            Intent i3 = new Intent(v.getContext(),CardDemobordsa.class);
                            v.getContext().startActivity(i3);
                            break;
                    }

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


}