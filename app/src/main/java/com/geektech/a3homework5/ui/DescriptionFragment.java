package com.geektech.a3homework5.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geektech.a3homework5.data.model.FilmModel;
import com.geektech.a3homework5.data.network.FilmRepository;
import com.geektech.a3homework5.databinding.FragmentDescriptionBinding;
import com.geektech.a3homework5.ui.film.FilmFragment;
import com.geektech.a3homework5.ui.saved.SavedFragment;

import org.jetbrains.annotations.NotNull;

public class DescriptionFragment extends Fragment {

    protected FragmentDescriptionBinding binding;
    protected String id;
    protected FilmModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDescriptionBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            methodFromFilmFragment();

            methodFromSavedFragment();
        }
    }

    private void methodFromSavedFragment() {
        model = (FilmModel) getArguments().getSerializable(SavedFragment.MODEL);
        if (model != null) {
            setTextModel(model);
        }
    }

    private void methodFromFilmFragment() {
        id = getArguments().getString(FilmFragment.ID);
        if (id != null) {
            getFilmById(id);
        }
    }

    private void getFilmById(String id) {
        FilmRepository.getFilmById(id, new FilmRepository.FilmByIdCallback() {
            @Override
            public void onSuccess(FilmModel model) {
                setTextModel(model);
            }

            @Override
            public void onFailure(String message) {
            }
        });
    }

    private void setTextModel(FilmModel model) {
        binding.textTitleFragmentDescription.setText(model.getTitle());
        binding.textDescriptionFragmentDescription.setText(model.getDescription());
    }
}