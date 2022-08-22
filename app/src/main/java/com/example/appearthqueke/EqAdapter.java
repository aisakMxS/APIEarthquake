package com.example.appearthqueke;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appearthqueke.databinding.EqListItemsBinding;

//alt+Enter -> class -> Implementar metodos (onCreate/onBind)
//alt+Enter -> List -> genera el constructor (Diff)
public class EqAdapter extends ListAdapter<Earthquake, EqAdapter.EqViewHolder> {

    public static final DiffUtil.ItemCallback<Earthquake> DIFF_CALLBACK = new DiffUtil.ItemCallback<Earthquake>() {
        @Override
        public boolean areItemsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Earthquake oldItem, @NonNull Earthquake newItem) {
            return oldItem.equals(newItem);
        }
    };

    protected EqAdapter() {
        super(DIFF_CALLBACK);
    }

    private OnItemClickListener onItemClickListener;

    interface  OnItemClickListener{
        void onItemClick(Earthquake earthquake);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public EqAdapter.EqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eq_list_items, parent, false);


        EqListItemsBinding binding = EqListItemsBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new EqViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EqAdapter.EqViewHolder holder, int position) {
        Earthquake earthquake = getItem(position);
        holder.bind(earthquake);
    }

    //
    class EqViewHolder extends RecyclerView.ViewHolder{
        //private TextView magnitudeText;
        //private TextView placeText;

        private EqListItemsBinding binding;
        public EqViewHolder(@NonNull EqListItemsBinding binding) {
            super(binding.getRoot());
            //magnitudeText = itemView.findViewById(R.id.magnitude_text);
            //placeText = itemView.findViewById(R.id.place_text);
            this.binding = binding;
        }

        public void bind(Earthquake earthquake) {
            binding.magnitudeText.setText(String.valueOf(earthquake.getMangnitude()));
            binding.placeText.setText(earthquake.getPlace());

            binding.getRoot().setOnClickListener(v -> {
                onItemClickListener.onItemClick(earthquake);
            });

            binding.executePendingBindings();
        }
    }
}
