package fr.gjandot.trombidep;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * The ImageListPreference class responsible for displaying an image for each
 * item within the list.
 */
public class IconCheckBoxPreference extends CheckBoxPreference {
    private Drawable mIcon;
    
    public IconCheckBoxPreference(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);  
        this.setLayoutResource(R.layout.ico_cbox_pref); 
        this.mIcon = context.obtainStyledAttributes(attrs, R.styleable.IconPreference, defStyle, 0).getDrawable(R.styleable.IconPreference_icon);
    }

    public IconCheckBoxPreference(final Context context, final AttributeSet attrs) {
        //this(context, attrs, 0);
        super(context, attrs);   
        this.setLayoutResource(R.layout.ico_cbox_pref2);       
        this.mIcon = context.obtainStyledAttributes(attrs, R.styleable.IconPreference, 0, 0).getDrawable(R.styleable.IconPreference_icon);
    }

    @Override
    protected void onBindView(final View view) {
        super.onBindView(view);
        
        final ImageView imageView = (ImageView)view.findViewById(R.id.icon);
        if ((imageView != null) && (this.mIcon != null)) {
            imageView.setImageDrawable(this.mIcon);
        }
    }

    /**
* Sets the icon for this Preference with a Drawable.
*
* @param icon The icon for this Preference
*/
    /*public void setIcon(final Drawable icon) {
        if (((icon == null) && (this.mIcon != null)) || ((icon != null) && (!icon.equals(this.mIcon)))) {
            this.mIcon = icon;
            this.notifyChanged();
        }
    }*/

    /**
* Returns the icon of this Preference.
*
* @return The icon.
* @see #setIcon(Drawable)
*/
    /*public Drawable getIcon() {
        return this.mIcon;
    }*/
}