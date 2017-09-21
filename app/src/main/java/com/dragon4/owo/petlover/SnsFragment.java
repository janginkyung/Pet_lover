package com.dragon4.owo.petlover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dragon4.owo.petlover.model.Sns;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class SnsFragment extends Fragment {
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton floatingActionButton;

    ViewGroup rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_sns, container, false);
        floatingActionButton = (FloatingActionButton)rootView.findViewById(R.id.add_sns_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WriteSNSActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView reviewRecyclerView = (RecyclerView)rootView.findViewById(R.id.sns_recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        reviewRecyclerView.setLayoutManager(mLayoutManager);
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.scrollToPosition(0);

        final ArrayList<Sns> snsList = new ArrayList<>();

        final SnsAdapter snsAdapter = new SnsAdapter(snsList);
        reviewRecyclerView.setAdapter(snsAdapter);
        reviewRecyclerView.setItemAnimator(new DefaultItemAnimator());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("sns").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                snsList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Sns snsObj = child.getValue(Sns.class);
                    snsList.add(snsObj);
                }
                snsAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // TODO: 2017. 6. 16. sns 가져오기




        // TODO: 2017. 6. 16. 파이어 베이스에서 가져오기.

        return rootView;
    }

    public class SnsAdapter extends RecyclerView.Adapter<SnsAdapter.ViewHolder> {
        private ArrayList<Sns> snsInfos;

        public SnsAdapter(ArrayList<Sns> reviews) {
            this.snsInfos = reviews;
        }

        @Override
        public SnsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sns, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Sns snsInfo = snsInfos.get(position);
            Picasso.with(getContext()).load(snsInfo.getUserImageUrl()).into(holder.profileImageView);
            holder.profileNameTextVIew.setText(snsInfo.getUserName());
            holder.snsDateTextView.setText(snsInfo.getDate());
            Picasso.with(getContext()).load(snsInfo.getSnsImageUrl()).into(holder.snsImageView);
            holder.snsTextView.setText(snsInfo.getSnsText());
        }

        @Override
        public int getItemCount() {
            return snsInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageView profileImageView;
            public TextView  profileNameTextVIew;
            public TextView  snsDateTextView;
            public ImageView snsImageView;
            public TextView  snsTextView;


            public ViewHolder(View view) {
                super(view);
                profileImageView = (ImageView) view.findViewById(R.id.profileImageview);
                profileNameTextVIew = (TextView) view.findViewById(R.id.profileNameTextVIew);
                snsDateTextView = (TextView) view.findViewById(R.id.snsDateTextView);
                snsImageView  = (ImageView) view.findViewById(R.id.snsImageView);
                snsTextView = (TextView) view.findViewById(R.id.snsTextView);

            }
        }

    }



}
