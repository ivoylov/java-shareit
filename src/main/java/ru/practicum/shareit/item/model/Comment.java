package ru.practicum.shareit.item.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments", schema = "public")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name="booker_id", nullable = false)
    private User booker;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Item.class)
    @JoinColumn(name="item_id", nullable = false)
    private Item item;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="comment_text")
    private String text;
}
