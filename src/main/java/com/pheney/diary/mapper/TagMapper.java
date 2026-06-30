package com.pheney.diary.mapper;

import com.pheney.diary.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TagMapper {

    Tag findByUserIdAndName(@Param("userId") Long userId,
                             @Param("name") String name);

    void insert(Tag tag);

    void updateName(@Param("id") Long id,
                    @Param("name") String name);

    List<Tag> findAllByUserId(@Param("userId") Long userId);

    void deleteById(@Param("id") Long id);

    List<Map<String, Object>> findTagsWithCount(@Param("userId") Long userId);
}
