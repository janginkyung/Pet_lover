package com.dragon4.owo.petlover;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dragon4.owo.petlover.model.Plan;
import com.dragon4.owo.petlover.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VolunteerDetailActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_detail);

        RecyclerView reviewRecyclerView = (RecyclerView)findViewById(R.id.voluteerPlanReyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        reviewRecyclerView.setLayoutManager(mLayoutManager);
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.scrollToPosition(0);

        final ArrayList<Plan> planArrayList = new ArrayList<>();

        final PlanAdapter planAdapter = new PlanAdapter(planArrayList);
        reviewRecyclerView.setAdapter(planAdapter);
        reviewRecyclerView.setItemAnimator(new DefaultItemAnimator());

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("plan");
        databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    planArrayList.clear();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Plan planObj = child.getValue(Plan.class);
                        planArrayList.add(planObj);
                    }
                    planAdapter.notifyDataSetChanged();
                }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
        private ArrayList<Plan> planInfos;

        public PlanAdapter(ArrayList<Plan> planInfos) {
            this.planInfos = planInfos;
        }

        @Override
        public PlanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_plan, parent, false);

            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final Plan planInfo = planInfos.get(position);
            holder.planTitleView.setText(planInfo.getTitle());
            holder.planStatevIEW.setText("참가자 " + planInfo.getNum() +"명 " + planInfo.getUserId());
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    planInfo.setNum(planInfo.getNum() + 1);
                    planInfo.setUserId(planInfo.getUserId() + ", " + User.getInstance().getUserEmail());
                    holder.planStatevIEW.setText("참가자 " + (planInfo.getNum()) +"명 " + planInfo.getUserId());
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("plan");
                    databaseReference.child(planInfo.getTitle()).setValue(planInfo);
                }
            });
        }

        @Override
        public int getItemCount() {
            return planInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ConstraintLayout constraintLayout;
            public TextView planTitleView;
            public TextView  planStatevIEW;

            public ViewHolder(View view) {
                super(view);
                constraintLayout = (ConstraintLayout) view.findViewById(R.id.card_plan_holder);
                planTitleView = (TextView) view.findViewById(R.id.planTileView);
                planStatevIEW = (TextView) view.findViewById(R.id.planStateView);


            }
        }

    }


}
