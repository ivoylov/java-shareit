package ru.practicum.shareit.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;
import ru.practicum.shareit.item.model.Item;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shareit_user", schema = "public")
public class User {

    public static final int MAX_NAME_LENGTH = 20;
    public static final int MIN_NAME_LENGTH = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(name = "user_email", nullable = false)
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    List<Item> items;

    public String toString() {
        return String.format("id=%d name=%s, email = %s", id, name, email);
    }

}
