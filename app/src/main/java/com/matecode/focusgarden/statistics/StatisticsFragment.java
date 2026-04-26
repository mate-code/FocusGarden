package com.matecode.focusgarden.statistics;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.matecode.focusgarden.R;
import com.matecode.focusgarden.Utils.CalculateMinutesPerHour;
import com.matecode.focusgarden.databinding.FragmentStatisticsBinding;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.matecode.focusgarden.db.AppDatabase;
import com.matecode.focusgarden.db.session.Session;
import com.matecode.focusgarden.db.session.SessionRepository;
import com.matecode.focusgarden.db.session.SessionWithCategory;
import com.matecode.focusgarden.db.user.User;
import com.matecode.focusgarden.db.user.UserDao;
import com.matecode.focusgarden.db.user.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsFragment extends Fragment {
    private FragmentStatisticsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(requireContext());

            UserRepository userRepo = new UserRepository(db.userDao());
            User user = userRepo.getUserById("0-0-0");

            SessionRepository sessionRepo = new SessionRepository(db.sessionDao());
            List<Session> userSessions = sessionRepo.getUserSessions(user.getId());
            List<SessionWithCategory> userSessionsWithCategories = sessionRepo.getUserSessionsWithCategories(user.getId());

            Log.println(Log.DEBUG, StatisticsFragment.class.getSimpleName(), "DB data were loaded");

            Map<String, List<BarEntry>> dataEntries = new HashMap<>();
            Map<String, Integer> nameAndColor = new HashMap<>();
            for (SessionWithCategory userCategoryWithSession: userSessionsWithCategories) {
                dataEntries.putIfAbsent(userCategoryWithSession.getCategoryName(), new ArrayList<BarEntry>());
                nameAndColor.putIfAbsent(userCategoryWithSession.getCategoryName(), userCategoryWithSession.getCategoryColor());
                Map<Integer, Integer> dict = CalculateMinutesPerHour.calculate(userCategoryWithSession.getSessionStart(), userCategoryWithSession.getSessionEnd());
                for (Map.Entry<Integer, Integer> entry: dict.entrySet()){
                    dataEntries.get(userCategoryWithSession.getCategoryName()).add(new BarEntry(entry.getKey(), entry.getValue()));
                }
            }

            List<BarDataSet> sets = new ArrayList<BarDataSet>();
            for (Map.Entry<String, List<BarEntry>> entry: dataEntries.entrySet()) {
                BarDataSet set = new BarDataSet(entry.getValue(), entry.getKey());
                int color = nameAndColor.getOrDefault(entry.getKey(), 0);
                set.setColor(color);
                sets.add(set);
            }

            BarChart barChart = binding.barChart;

            XAxis xAxis = barChart.getXAxis();
            YAxis yAxis = barChart.getAxisLeft();
            yAxis.setAxisMinimum(0);
            yAxis.setAxisMaximum(60);
            xAxis.setAxisMinimum(0f);
            xAxis.setAxisMaximum(24f);
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            BarData barData = new BarData(sets.toArray(new IBarDataSet[0]));
            barChart.setData(barData);
            barChart.invalidate();

        }).start();


        binding.btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_StatisticsFragment_to_GardenFragment);
            }
        });
    }
}
