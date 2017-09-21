package com.dragon4.owo.petlover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dragon4.owo.petlover.model.Community;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by InKyung on 2017. 6. 16..
 */

public class CommunityFragment extends Fragment {

    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);


        FloatingActionButton floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.add_community_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),WriteCommuityActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView reviewRecyclerView = (RecyclerView)rootView.findViewById(R.id.commnunity_recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());
        reviewRecyclerView.setLayoutManager(mLayoutManager);
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.scrollToPosition(0);

        final ArrayList<Community> communityList = new ArrayList<>();

        final CommunityAdapter communityAdapter = new CommunityAdapter(communityList);
        reviewRecyclerView.setAdapter(communityAdapter);
        reviewRecyclerView.setItemAnimator(new DefaultItemAnimator());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().child("community").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                communityList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Community snsObj = child.getValue(Community.class);
                    communityList.add(snsObj);
                }
                communityAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }

    public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
        private ArrayList<Community> communities;

        public CommunityAdapter(ArrayList<Community> communities) {
            this.communities = communities;
        }

        @Override
        public CommunityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_community, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Community community = communities.get(position);
            holder.communityTitle.setText(community.getCommunityTitle());
            holder.communityStatus.setText("댓글 : " + community.getReviewNum() + "개 " + "조회 : " +community.getCountNum() +"회");
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    community.setCountNum(community.getCountNum()+1);
                    holder.communityStatus.setText("댓글 : " + community.getReviewNum() + "개 " + "조회 : " +community.getCountNum() +"회");
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("community");
                    databaseReference.child(community.getCommunityTitle()).setValue(community);

                   // Intent intent = new Intent(getActivity(),CommunityDetailActivity.class);
                   // startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return communities.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ConstraintLayout constraintLayout;
            public TextView communityTitle;
            public TextView communityStatus;

            public ViewHolder(View view) {
                super(view);
                constraintLayout= (ConstraintLayout) view.findViewById(R.id.card_community_holder);
                communityTitle = (TextView) view.findViewById(R.id.communityTextview);
                communityStatus = (TextView) view.findViewById(R.id.communiStateView);

            }
        }

    }





}
