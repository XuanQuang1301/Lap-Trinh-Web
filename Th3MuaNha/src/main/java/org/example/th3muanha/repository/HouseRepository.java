package org.example.th3muanha.repository;

import org.example.th3muanha.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, String> {
    @Query("SELECT h FROM House h WHERE " +
    "(:id != '' AND h.id = :id) OR " +
            "( :address != '' AND h.address LIKE %:address% ) OR " +
            "( :area IS NOT NULL AND h.area = :area ) OR " +
            "( :type != '' AND h.type LIKE %:type% )"
    )
    List<House> searchHouseByAtLeastOnField(
            @Param("id") String id,
            @Param("address") String address,
            @Param("area") Integer area,
            @Param("type") String type
    );
}
