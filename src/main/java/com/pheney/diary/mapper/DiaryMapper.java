package com.pheney.diary.mapper;

import com.pheney.diary.entity.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface DiaryMapper {

    void insert(Diary diary);

    Diary findById(@Param("id") Long id);

    void update(Diary diary);

    void deleteById(@Param("id") Long id);

    List<Diary> findByUserId(@Param("userId") Long userId,
                             @Param("offset") int offset,
                             @Param("limit") int limit);

    long countByUserId(@Param("userId") Long userId);

    List<Diary> findByUserIdAndDate(@Param("userId") Long userId,
                                     @Param("date") LocalDate date);

    List<Diary> findByUserIdAndYearMonth(@Param("userId") Long userId,
                                          @Param("year") int year,
                                          @Param("month") int month);

    List<Diary> search(@Param("userId") Long userId,
                        @Param("keyword") String keyword,
                        @Param("offset") int offset,
                        @Param("limit") int limit);

    long countSearch(@Param("userId") Long userId,
                     @Param("keyword") String keyword);

    List<Diary> filter(@Param("userId") Long userId,
                        @Param("keyword") String keyword,
                        @Param("type") String type,
                        @Param("mood") String mood,
                        @Param("tag") String tag,
                        @Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        @Param("offset") int offset,
                        @Param("limit") int limit);

    long countFilter(@Param("userId") Long userId,
                     @Param("keyword") String keyword,
                     @Param("type") String type,
                     @Param("mood") String mood,
                     @Param("tag") String tag,
                     @Param("startDate") LocalDate startDate,
                     @Param("endDate") LocalDate endDate);

    long countTotalByUserId(@Param("userId") Long userId);

    long countThisMonthByUserId(@Param("userId") Long userId,
                                 @Param("year") int year,
                                 @Param("month") int month);

    List<Map<String, Object>> countByType(@Param("userId") Long userId);
}
