package com.example.penaliz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FineList extends AppCompatActivity {
    private ListView fineList;
    List<Traffic> mTraffic;
    DatabaseReference databaseArtiest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_list);
        fineList = (ListView) findViewById(R.id.list);
        databaseArtiest = FirebaseDatabase.getInstance().getReference("abcd");
        mTraffic = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtiest.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTraffic.clear();
                for (DataSnapshot artiestSnapshot : dataSnapshot.getChildren()) {
                    Traffic traffic = artiestSnapshot.getValue(Traffic.class);
                    mTraffic.add(traffic);

                }
                Adapter arrayAdapter = new Adapter(FineList.this, mTraffic);
                fineList.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
