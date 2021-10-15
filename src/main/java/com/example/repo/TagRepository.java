package com.example.repo;

import com.example.models.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tags, Long> {

    @Query(value = "SELECT * FROM public.tag WHERE tag LIKE CONCAT('%',:param,'%')", nativeQuery = true)
    List<Tags> findAll(@Param("param") String param);

    @Query(value = "SELECT count(*) FROM tag", nativeQuery = true)
    Integer sizeDB();


}
