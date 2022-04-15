package kg.peaksoft.peaksoftlmsab4.repository;

import kg.peaksoft.peaksoftlmsab4.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
