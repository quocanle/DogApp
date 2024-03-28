package com.example.dogapp.viewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

    public DogAdapter(ArrayList<DogBreed> dogBreeds, Context context) {
        this.dogBreeds = dogBreeds;
        this.context = context;
    }

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

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(DogItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
//            binding.llDogLessInformation.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DogBreed dog = dogBreeds.get(getAdapterPosition());
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("dog", dog);
//                    Navigation.findNavController(v).navigate(R.id.detailsFragment, bundle);
//                }
//            });

            itemView.setOnTouchListener(new OnSwipeTouchListener(context, itemView) {
                @Override
                public void onSwipeLeft() {
                    if (binding.llDogFullInformation.getVisibility() == View.GONE) {
                        binding.llDogFullInformation.setVisibility(View.VISIBLE);
                        binding.llDogLessInformation.setVisibility(View.GONE);
                        DogBreed dog = dogBreeds.get(getAdapterPosition());
                        TextView tvHeight = binding.llDogFullInformation.findViewById(R.id.tv_height);
                        tvHeight.setText(dog.getHeight().getMetric());
                        TextView tvWeight = binding.llDogFullInformation.findViewById(R.id.tv_weight);
                        tvWeight.setText(dog.getWeight().getMetric());
                    }
                }
                @Override
                public void onSwipeRight() {
                    if (binding.llDogFullInformation.getVisibility() == View.VISIBLE) {
                        binding.llDogFullInformation.setVisibility(View.GONE);
                        binding.llDogLessInformation.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onSingleTap() {
                    DogBreed dog = dogBreeds.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("dog", dog);
                    Navigation.findNavController(itemView).navigate(R.id.detailsFragment, bundle);
                }
            });
//            binding.llDogFullInformation.setOnTouchListener(new OnSwipeTouchListener(context, itemView) {
//                @Override
//                public void onSwipeRight() {
//                    if (binding.llDogFullInformation.getVisibility() == View.VISIBLE) {
//                        binding.llDogFullInformation.setVisibility(View.GONE);
//                        binding.llDogLessInformation.setVisibility(View.VISIBLE);
//                    }
//                }
//            });
        }
    }

    public class OnSwipeTouchListener implements View.OnTouchListener {

        protected final GestureDetector gestureDetector;
        private View view;

        public OnSwipeTouchListener (Context ctx, View view){
            gestureDetector = new GestureDetector(ctx, new GestureListener());
            this.view = view;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                onSingleTap();
                return true;
            }
        }

        public void onSingleTap() {
        }

        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }
    }
}
