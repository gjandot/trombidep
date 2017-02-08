package fr.gjandot.trombidep;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class Prefs extends PreferenceActivity {
    private ImageListPreference mListPref;   
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	Preference.OnPreferenceChangeListener change_syst = new OnPreferenceChangeListener() {

	    @Override
	    public boolean onPreferenceChange(Preference preference, Object newValue) {
			int i = Integer.parseInt(newValue.toString());
			return true;
	    }
	};
	
}
