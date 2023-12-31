package com.kerbygregorio.library.ui.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.kerbygregorio.library.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_settings, container, false);

        //load ntn ang sharedprefs
        //PALITAN UNG PACKAGE NAME NG PACKAGE NAME NYO
        SharedPreferences prefs = root.getContext().getSharedPreferences("com.kerbygregorio.library", Context.MODE_PRIVATE);

        //load natin yung default setting sa prefs tungkol sa darkmode.
        //false ung default value, kng sakaling wala pang na-save na ganyan dati
        //ung text na "enableDarkMode" ay ung "key"
        //bawat item na sinasave sa SharedPrefs dapat may sariling key
        boolean isOn = prefs.getBoolean("enableDarkMode", false);

        Switch enableDarkMode = root.findViewById(R.id.enableDarkMode);

        //Ung niload nating value kanina, un ung iseset natin dto
        //Para kung naka-on sya dati, naka-on din ung hitsura ng switch natin
        enableDarkMode.setChecked(isOn);

        //Pag nagbago ung Switch, ito ang ma-trigger
        enableDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Ito specifically
                if(isChecked){
                    //Ito ung nag-eenable ng night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //Sympre, i-save natin sa SharedPrefs ung bagong value ng switch natin
                    prefs.edit().putBoolean("enableDarkMode", isChecked).commit();
                }else{
                    //Ito ung nag-didisable ng night mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //Sympre, i-save natin sa SharedPrefs ung bagong value ng switch natin
                    prefs.edit().putBoolean("enableDarkMode", isChecked).commit();
                }
            }
        });

        return root;
    }
}