package com.example.toolsmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowTools extends AppCompatActivity {
ToolsListN toolsListN;
TextView tvName,tvUid, tvAvlblty, tvDetails, tvIssued,tvIssuedo, tvReturn, tvReturno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tools);
        tvName= findViewById(R.id.ast_tvNameo);
        tvUid= findViewById(R.id.ast_tvUido);
        tvAvlblty= findViewById(R.id.ast_tvAvlbltyo);
        tvDetails= findViewById(R.id.ast_tvDetailso);
        tvIssued= findViewById(R.id.ast_issued);
        tvIssuedo= findViewById(R.id.ast_issuedo);
        tvReturn= findViewById(R.id.ast_tvreturndate);
        tvReturno= findViewById(R.id.ast_tvreturndateo);

        Intent intent= getIntent();
        Long uid= intent.getLongExtra("uid",0);
        FirebaseDatabase.getInstance().getReference().child("tools").child(uid.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toolsListN= dataSnapshot.getValue(ToolsListN.class);
                tvName.setText(toolsListN.name);
                tvUid.setText(toolsListN.uid.toString());
                tvDetails.setText(toolsListN.details);

                if(toolsListN.availability==false){
                    tvAvlblty.setText("Not Available");
                    tvIssuedo.setText(toolsListN.issued_to);
                    tvReturno.setText(toolsListN.return_date);
                }
                else {
                    tvAvlblty.setText("Available");
                    tvIssued.setVisibility(View.INVISIBLE);
                    tvIssuedo.setVisibility(View.INVISIBLE);
                    tvIssuedo.setEnabled(false);
                    tvReturn.setVisibility(View.INVISIBLE);
                    tvReturno.setVisibility(View.INVISIBLE);
                    tvReturno.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        View.OnClickListener onClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("club_profile").child(tvIssuedo.getText().toString());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            Intent mIntent= new Intent(ShowTools.this,ShowProfile.class);
                            mIntent.putExtra("club", tvIssuedo.getText().toString());
                            mIntent.putExtra("mode",2);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                databaseReference= FirebaseDatabase.getInstance().getReference("individual_profile").child(tvIssuedo.getText().toString());
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            Intent mIntent= new Intent(ShowTools.this,ShowProfile.class);
                            mIntent.putExtra("roll",tvIssuedo.getText().toString());
                            mIntent.putExtra("mode",3);
                            startActivity(mIntent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        };
        tvIssuedo.setOnClickListener(onClickListener);

    }
}
