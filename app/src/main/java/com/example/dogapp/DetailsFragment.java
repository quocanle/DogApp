package com.example.dogapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogapp.databinding.FragmentDetailsBinding;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

public class DetailsFragment extends Fragment {
    private DogBreed dogBreed;
    private FragmentDetailsBinding binding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dogBreed = (DogBreed) getArguments().getSerializable("dog");
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        View viewRoot = binding.getRoot();
        Picasso.get().load(dogBreed.getUrl()).into(binding.ivDog);
//        binding.tvOrigin.setText(dogBreed.getOrigin());
        binding.tvHeight.setText(dogBreed.getHeight().getMetric());
        binding.tvWeight.setText(dogBreed.getWeight().getMetric());
        binding.setDog(dogBreed);
        return viewRoot;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_back, menu);
         menu.findItem(R.id.app_bar_back).setOnMenuItemClickListener(item -> {
//            getActivity().onBackPressed();
            getParentFragmentManager().popBackStack();
            return true;
        });
    }
}