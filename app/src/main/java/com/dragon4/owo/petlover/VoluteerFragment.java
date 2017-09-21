package com.dragon4.owo.petlover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class VoluteerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_voluteer, container, false);
        rootView.findViewById(R.id.first_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),VolunteerDetailActivity.class);
                startActivity(intent);

            }
        });
        rootView.findViewById(R.id.second_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),VolunteerDetailActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
