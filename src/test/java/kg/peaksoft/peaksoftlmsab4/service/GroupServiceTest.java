package kg.peaksoft.peaksoftlmsab4.service;

import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RequiredArgsConstructor
public class GroupServiceTest {
    @Autowired
    private GroupRepository groupRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveGroupTest() {
        GroupEntity groupEntity = GroupEntity.builder()
                .groupName("Peaksoft")
                .description("description")
                .image("image")
                .dateOfStart(LocalDate.now())
                .build();

        groupRepository.save(groupEntity);
        assertThat(groupEntity.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getGroupTest() {
        GroupEntity group = groupRepository.findById(1L).get();
        assertThat(group.getId()).isEqualTo(1l);
    }

    @Test
    @Order(3)
    public void getListGroupTest() {
        List<GroupEntity> groups = groupRepository.findAll();
        assertThat(groups.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateGroupTest() {
        GroupEntity group = groupRepository.findById(1L).get();
        group.setGroupName("Megacom");
        GroupEntity groupUpdated = groupRepository.save(group);
        assertThat(groupUpdated.getGroupName()).isEqualTo("Megacom");
    }

    @Test
    @Order(5)
    public void deleteGroupTest() {
        GroupEntity group = groupRepository.findById(1L).get();
        groupRepository.delete(group);
        boolean expected = groupRepository.existsById(1L);
        assertThat(expected).isFalse();
    }
}
