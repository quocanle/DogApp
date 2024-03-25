package com.example.dogapp.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dogapp.R;
import com.example.dogapp.databinding.DogItemBinding;
import com.example.dogapp.model.DogBreed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> implements Filterable {
    private ArrayList<DogBreed> dogBreeds;
    private Context context;

    public DogAdapter(ArrayList<DogBreed> dogBreeds) {
        this.dogBreeds = dogBreeds;
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dog_item, parent, false);
        DogItemBinding binding = DogItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {
        holder.binding.setDog(dogBreeds.get(position));
        Picasso.get().load(dogBreeds.get(position).getUrl()).into(holder.binding.ivDog);
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String input = constraint.toString().toLowerCase();
                ArrayList<DogBreed> filteredList = new ArrayList<>();
                if (input.isEmpty()) {
                    filteredList.addAll(dogBreeds);
                } else {
                    for (DogBreed dog : dogBreeds) {
//                        dog.getBred_for().toLowerCase().contains(input)
                        if (dog.getName().toLowerCase().contains(input)) {
                            filteredList.add(dog);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dogBreeds.clear();
                dogBreeds.addAll((ArrayList) results.values);
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public DogItemBinding binding;
        private ImageView ivDog;
        private TextView tvName;
        private TextView tvBreedFor;

        public ViewHolder(DogItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.llElement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DogBreed dog = dogBreeds.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dog", dog);
                    Navigation.findNavController(v).navigate(R.id.detailsFragment, bundle);
                }
            });
        }
    }
}
