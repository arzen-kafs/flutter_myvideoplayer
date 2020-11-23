package com.example.hikaioffline.hikaiplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hikaioffline.R;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    private List<Chapter> chapterList;

    RecyclerViewClickInterface recyclerViewClickInterface;

    ChapterAdapter(List<Chapter> chapterList,RecyclerViewClickInterface recyclerViewClickInterface) {
        this.chapterList = chapterList;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_chapter, viewGroup, false);
        return new ChapterViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder chapterViewHolder, int i) {
        Chapter chapter = chapterList.get(i);
        chapterViewHolder.tvChapterTitle.setText(chapter.getChapterTitle());
        chapterViewHolder.tvTotalLecture.setText(chapter.getTotalLecture());

        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                chapterViewHolder.rvLecture.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(chapter.getLectureList().size());

        // Create sub item view adapter
        LectureAdapter lectureAdapter = new LectureAdapter(chapter.getLectureList(),recyclerViewClickInterface);

        chapterViewHolder.rvLecture.setLayoutManager(layoutManager);
        chapterViewHolder.rvLecture.setAdapter(lectureAdapter);
        chapterViewHolder.rvLecture.setRecycledViewPool(viewPool);
    }

    @Override
    //Done
    public int getItemCount() {
        return chapterList.size();
    }

    class ChapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tvChapterTitle;
        private TextView tvTotalLecture;
        private RecyclerView rvLecture;

        ChapterViewHolder(View chapterView) {
            super(chapterView);
            tvChapterTitle = chapterView.findViewById(R.id.tv_chapter_title);
            tvTotalLecture = chapterView.findViewById(R.id.tv_total_lecture);
            rvLecture = chapterView.findViewById(R.id.rv_lecture);
        }
    }
}