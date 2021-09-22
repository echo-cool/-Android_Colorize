package com.example.myapplication.ui.ClarityEnhancement;

import android.os.Bundle;
import android.os.Environment;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.ImageUtil.rcImage;
import com.example.myapplication.R;
import com.example.myapplication.Util;
import com.example.myapplication.databinding.FragmentClarityEnhancementBinding;
import com.example.myapplication.ui.BaseFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ClarityEnhancementFragment extends BaseFragment {

    private ClarityEnhancementViewModel model;
    private FragmentClarityEnhancementBinding binding;
    private RecyclerView.Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        model =
                new ViewModelProvider(this).get(ClarityEnhancementViewModel.class);


        binding = FragmentClarityEnhancementBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Fade slideTracition = new Fade();
        slideTracition.setDuration(getResources().getInteger(R.integer.config_navAnimTime));
        this.setEnterTransition(slideTracition);
        this.setExitTransition(slideTracition);

        final TextView textView = binding.textGallery;
        model.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        RecyclerView image_gallery = binding.rcGallery;
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        adapter = new ImageAdapter(getRcImageList());
        image_gallery.setLayoutManager(layoutManager);
        image_gallery.setAdapter(adapter);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //防止item 交换位置
        image_gallery.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                layoutManager.invalidateSpanAssignments(); //防止第一行到顶部有空白区域
            }
        });


        return root;
    }
    private List<String> getImagePathList(){
        List<String> data = new ArrayList<>();
        File skRoot = Environment.getExternalStorageDirectory();
        data = Util.getFilesAllName(skRoot.getPath()+"/Colorize");
        return data;
    }
    private List<rcImage> getRcImageList(){
        List<String> filepathList = getImagePathList();
        List<rcImage> data = new ArrayList<>();
        for (String tmp:filepathList) {
            data.add(new rcImage(tmp.split("/")[tmp.split("/").length-1], tmp));
        }
        return data;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}