package fr.gjandot.trombidep;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.preference.ListPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;

/**
 * The ImageListPreference class responsible for displaying an image for each
 * item within the list.
 */
public class ImageListPreference extends ListPreference {
	private int[] resourceIds = null;
	private Drawable icon = null;

	/**
	 * Constructor of the ImageListPreference. Initializes the custom images.
	 * @param context application context.
	 * @param attrs custom xml attributes.
	 */
	public ImageListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setLayoutResource(R.layout.ico_pref);

		TypedArray typedArray = context.obtainStyledAttributes(attrs,
			R.styleable.ImageListPreference);

		String[] imageNames = context.getResources().getStringArray(
			typedArray.getResourceId(typedArray.getIndexCount()-1, -1));

		typedArray = context.obtainStyledAttributes(attrs,
				R.styleable.IconPreference);
		icon = typedArray.getDrawable(R.styleable.IconPreference_icon);

		resourceIds = new int[imageNames.length];

		for (int i=0;i<imageNames.length;i++) {
			String imageName = imageNames[i].substring(
				imageNames[i].indexOf('/') + 1,
				imageNames[i].lastIndexOf('.'));

			resourceIds[i] = context.getResources().getIdentifier(imageName,
				null, context.getPackageName());
		}

		typedArray.recycle();
	}
	/**
	 * {@inheritDoc}
	 */
	protected void onPrepareDialogBuilder(Builder builder) {
		int index = findIndexOfValue(getSharedPreferences().getString(
			getKey(), "1"));

		ListAdapter listAdapter = new ImageArrayAdapter(getContext(),
			R.layout.listitem, getEntries(), resourceIds, index);

		// Order matters.
		builder.setAdapter(listAdapter, this);
		super.onPrepareDialogBuilder(builder);
	}
	
    protected void onBindView(View view) {
        super.onBindView(view);

        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        if (imageView != null) {
            if (icon != null) {
                imageView.setImageDrawable(icon);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }
}
