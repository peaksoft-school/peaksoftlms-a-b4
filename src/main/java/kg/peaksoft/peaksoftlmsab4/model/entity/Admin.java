package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "admins")
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    AuthInfo authInfo;
}
