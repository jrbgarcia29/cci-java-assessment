package com.cci.assessment.repository;

import com.cci.assessment.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT task from Task as task where user.id = :userId")
    List<Task> findTasksByUser(Long userId);

    @Query("SELECT task from Task as task where user.id = :userId and id = :taskId")
    List<Task> findTasksByUserAndId(Long userId, Long taskId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TASKS SET STATUS = 'Done' WHERE STATUS = 'Pending' AND DATE_TIME < :dateTime ;" , nativeQuery = true)
    void updateLapsedTasks(String dateTime);
}
