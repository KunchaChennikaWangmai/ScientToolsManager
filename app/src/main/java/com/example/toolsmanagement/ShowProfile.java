package com.example.toolsmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShowProfile extends AppCompatActivity {
    TextView tvName, tvRoll, tvUid, tvTool, tvRollo;
    Long uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);
        tvName= findViewById(R.id.asp_tvnameo);
        tvRoll= findViewById(R.id.asp_tvroll);
        tvUid= findViewById(R.id.asp_currenttooluido);
        tvTool= findViewById(R.id.asp_currenttoolnameo);
        tvRollo= findViewById(R.id.asp_tvrollo);

        Intent intent= getIntent();
        int mode= intent.getIntExtra("mode",5);

        switch (mode) {
            case 0: ClubProfile clubProfile= (ClubProfile)intent.getSerializableExtra("data");
            tvName.setText(clubProfile.name);
            tvRoll.setVisibility(View.INVISIBLE);
            tvRollo.setVisibility(View.INVISIBLE);
            if(clubProfile.tools_history.size()!=0){
                SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date strdate= sdf.parse(clubProfile.tools_history.get(clubProfile.tools_history.size()-1).return_date);
                    if(new Date().after(strdate)) {
                        tvTool.setText(clubProfile.tools_history.get(clubProfile.tools_history.size()-1).name);
                        tvUid.setText(clubProfile.tools_history.get(clubProfile.tools_history.size()-1).uid.toString());
                        uid= clubProfile.tools_history.get(clubProfile.tools_history.size()-1).uid;
                    }

                    else {
                        tvTool.setText("No Tool");
                        tvTool.setEnabled(false);
                        tvUid.setEnabled(false);

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else {
                tvTool.setText("No Tool");
                tvTool.setEnabled(false);
                tvUid.setEnabled(false);
            }
            break;

            case 1: IndividualProfile individualProfile= (IndividualProfile)intent.getSerializableExtra("data");
            tvName.setText(individualProfile.name);
            tvRollo.setText(individualProfile.roll_No.toString());
            if(individualProfile.tools_history.size()!=0){
                SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date strdate= sdf.parse(individualProfile.tools_history.get(individualProfile.tools_history.size()-1).return_date);
                    if(new Date().after(strdate)) {
                        tvTool.setText(individualProfile.tools_history.get(individualProfile.tools_history.size()-1).name);
                        tvUid.setText(individualProfile.tools_history.get(individualProfile.tools_history.size()-1).uid.toString());
                        uid= individualProfile.tools_history.get(individualProfile.tools_history.size()-1).uid;
                    }
                    else {
                        tvTool.setText("No Tool");
                        tvTool.setEnabled(false);
                        tvUid.setEnabled(false);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else {
                tvTool.setText("No Tool");
                tvTool.setEnabled(false);
                tvUid.setEnabled(false);
            }
            break;

            case 2: String club= intent.getStringExtra("club");
            FirebaseDatabase.getInstance().getReference("club_profile").child(club).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ClubProfile clubProfile1= dataSnapshot.getValue(ClubProfile.class);
                    tvName.setText(clubProfile1.name);
                    tvRoll.setVisibility(View.INVISIBLE);
                    tvRollo.setVisibility(View.INVISIBLE);
                    if(clubProfile1.tools_history.size()!=0){
                        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date strdate= sdf.parse(clubProfile1.tools_history.get(clubProfile1.tools_history.size()-1).return_date);
                            if(new Date().after(strdate)) {
                                tvTool.setText(clubProfile1.tools_history.get(clubProfile1.tools_history.size()-1).name);
                                tvUid.setText(clubProfile1.tools_history.get(clubProfile1.tools_history.size()-1).uid.toString());
                                uid= clubProfile1.tools_history.get(clubProfile1.tools_history.size()-1).uid;
                            }
                            else {
                                tvTool.setText("No Tool");
                                tvTool.setEnabled(false);
                                tvUid.setEnabled(false);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        tvTool.setText("No Tool");
                        tvTool.setEnabled(false);
                        tvUid.setEnabled(false);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            break;

            case 3: String roll= intent.getStringExtra("roll");
            FirebaseDatabase.getInstance().getReference("individual_profile").child(roll).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    IndividualProfile individualProfile1= dataSnapshot.getValue(IndividualProfile.class);
                    tvName.setText(individualProfile1.name);
                    tvRollo.setText(individualProfile1.roll_No.toString());
                    if(individualProfile1.tools_history.size()!=0){
                        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
                        try {
                            Date strdate= sdf.parse(individualProfile1.tools_history.get(individualProfile1.tools_history.size()-1).return_date);
                            if(new Date().after(strdate)) {
                                tvTool.setText(individualProfile1.tools_history.get(individualProfile1.tools_history.size()-1).name);
                                tvUid.setText(individualProfile1.tools_history.get(individualProfile1.tools_history.size()-1).uid.toString());
                                uid= individualProfile1.tools_history.get(individualProfile1.tools_history.size()-1).uid;
                            }
                            else {
                                tvTool.setText("No Tool");
                                tvTool.setEnabled(false);
                                tvUid.setEnabled(false);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        tvTool.setText("No Tool");
                        tvTool.setEnabled(false);
                        tvUid.setEnabled(false);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            break;

            default: break;
        }

        View.OnClickListener onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1= new Intent(getApplicationContext(),ShowTools.class);
                intent1.putExtra("uid",uid);
                startActivity(intent1);
            }
        };
        tvTool.setOnClickListener(onClickListener);
        tvUid.setOnClickListener(onClickListener);
    }
}
