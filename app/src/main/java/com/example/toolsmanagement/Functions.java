package com.example.toolsmanagement;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Functions {

private FirebaseDatabase firebaseDatabase;
private DatabaseReference databaseReference;
private ArrayList<IndividualProfile> individualProfileList;
private ArrayList<ClubProfile> clubProfileList;
private ArrayList<ToolsListN> toolsListN;
private ClubProfileRecyclerViewAdapter clubProfileRecyclerViewAdapter;
private IndividualProfileRecyclerViewAdapter individualProfileRecyclerViewAdapter;
private ToolsRecyclerViewAdapter toolsRecyclerViewAdapter;
private Context context;
private ToolsListN tool;
private ClubProfile clubWithName;
private IndividualProfile individualWithName;

Functions(Context context) {
    this.context= context;
    firebaseDatabase= FirebaseDatabase.getInstance(); }

 IndividualProfileRecyclerViewAdapter ReturnAllIndividualProfile() {
    individualProfileList= new ArrayList<>();
    individualProfileRecyclerViewAdapter= new IndividualProfileRecyclerViewAdapter(individualProfileList,context);
    databaseReference= firebaseDatabase.getReference("individual_profile");
    databaseReference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            IndividualProfile individualProfile= dataSnapshot.getValue(IndividualProfile.class);
            individualProfileRecyclerViewAdapter.add(individualProfile);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    return individualProfileRecyclerViewAdapter;
}

ClubProfileRecyclerViewAdapter ReturnAllClubProfile() {
    clubProfileList= new ArrayList<>();
    clubProfileRecyclerViewAdapter= new ClubProfileRecyclerViewAdapter(clubProfileList,context);
    databaseReference= firebaseDatabase.getReference("club_profile");
    databaseReference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            ClubProfile clubProfile= dataSnapshot.getValue(ClubProfile.class);
            clubProfileRecyclerViewAdapter.add(clubProfile);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    return clubProfileRecyclerViewAdapter;
}

ToolsRecyclerViewAdapter ReturnAllTools() {
    toolsListN= new ArrayList<>();
    toolsRecyclerViewAdapter= new ToolsRecyclerViewAdapter(toolsListN,context);
    databaseReference= firebaseDatabase.getReference("tools");
    databaseReference.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            ToolsListN mToolsListN= dataSnapshot.getValue(ToolsListN.class);
            toolsRecyclerViewAdapter.add(mToolsListN);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    return toolsRecyclerViewAdapter;
}

ToolsListN ReturnToolsWithUid(Long Uid) {
    databaseReference= firebaseDatabase.getReference("tools").child(Uid.toString());
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            tool= dataSnapshot.getValue(ToolsListN.class);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    return tool;
}

ClubProfile ReturnClubWithName(String club) {
    databaseReference= firebaseDatabase.getReference("club_profile").child(club);
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            clubWithName= dataSnapshot.getValue(ClubProfile.class);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    return clubWithName;
}

IndividualProfile ReturnWithRoll(String roll) {
    databaseReference= firebaseDatabase.getReference("individual_profile").child(roll);
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            individualWithName= dataSnapshot.getValue(IndividualProfile.class);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    return individualWithName;
}
}
