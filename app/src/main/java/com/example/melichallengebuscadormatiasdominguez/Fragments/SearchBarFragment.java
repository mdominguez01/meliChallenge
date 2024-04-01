package com.example.melichallengebuscadormatiasdominguez.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.melichallengebuscadormatiasdominguez.R;
import com.example.melichallengebuscadormatiasdominguez.databinding.FragmentFirstBinding;

public class SearchBarFragment extends Fragment {

    private FragmentFirstBinding binding;
    private String textSearch;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       searchingText();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //toListFragment: navegar a la siguiente vista
    private void toListFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("search",textSearch);

                NavHostFragment.findNavController(SearchBarFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);

    }

    //searchingText: obtener texto del searchbar par buscarlo luego
    private void searchingText(){
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                toListFragment();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                textSearch = newText;
                return false;
            }
        });
    }
}