package com.example.arentacar.dataAccess.abstracts;

import com.example.arentacar.entities.concretes.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {

}
