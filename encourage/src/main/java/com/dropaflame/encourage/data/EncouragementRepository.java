package com.dropaflame.encourage.data;

import com.dropaflame.encourage.model.Encouragement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncouragementRepository extends JpaRepository<Encouragement,Long> {

}
