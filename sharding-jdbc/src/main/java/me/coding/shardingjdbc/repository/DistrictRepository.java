package me.coding.shardingjdbc.repository;

import me.coding.shardingjdbc.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

}
