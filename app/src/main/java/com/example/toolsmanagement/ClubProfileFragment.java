package com.example.toolsmanagement;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ClubProfileFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private ClubProfileRecyclerViewAdapter clubProfileRecyclerViewAdapter;
    EditText editText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClubProfileFragment() {
    }

    public ClubProfileFragment(ClubProfileRecyclerViewAdapter clubProfileRecyclerViewAdapter) {
        this.clubProfileRecyclerViewAdapter= clubProfileRecyclerViewAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView mRecyclerView = getView().findViewById(R.id.fc_rv);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(clubProfileRecyclerViewAdapter);

        editText= getView().findViewById(R.id.editTextSearch);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //new array list that will hold the filtered data
                ArrayList<ClubProfile> filterdProfiles = new ArrayList<>();
                List<ClubProfile> list= clubProfileRecyclerViewAdapter.getList();

                //looping through existing elements
                if (list!= null){
                    for (ClubProfile s : list) {
                        //if the existing elements contains the search input
                        if (s.getname().toLowerCase().contains(editable.toString().toLowerCase())) {
                            //adding the element to filtered list
                            filterdProfiles.add(s);
                        }
                    }
                    clubProfileRecyclerViewAdapter.setData(filterdProfiles);
                }

            }
        });
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(ClubProfile item);
    }
}
