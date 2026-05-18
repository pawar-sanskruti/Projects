package com.college.exam.service;

import com.college.exam.dto.SeatingDashboardDTO;
import com.college.exam.repository.SeatingDashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatingDashboardService {

    @Autowired
    private SeatingDashboardRepository repo;

    public List<SeatingDashboardDTO> getRecentSeating() {

        List<Object[]> rows = repo.getRecentSeating();
        List<SeatingDashboardDTO> list = new ArrayList<>();

        for (Object[] row : rows) {
            list.add(new SeatingDashboardDTO(
                    (String) row[0],  // exam_name
                    row[1].toString(), // exam_date
                    (String) row[2],  // subject_name
                    (String) row[3]   // hall_name
            ));
        }

        return list;
    }
}