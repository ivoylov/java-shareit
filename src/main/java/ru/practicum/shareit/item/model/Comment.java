package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.Create;
import ru.practicum.shareit.Update;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    private static final int MIN_COMMENT_LENGTH = 1;
    private static final int MAX_COMMENT_LENGTH = 255;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(groups = {Create.class, Update.class}, message = "комментарий должен содержать символы")
    @Size(groups = {Create.class, Update.class}, min = MIN_COMMENT_LENGTH, max = MAX_COMMENT_LENGTH, message = "некорректная длина комментария")
    @Column(nullable = false)
    private String text;
    @Column(name = "item_id", nullable = false)
    private Long itemId;
    @Column(name = "author_id", nullable = false)
    private Long authorId;
}
