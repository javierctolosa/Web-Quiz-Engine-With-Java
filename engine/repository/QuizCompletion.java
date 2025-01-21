package engine.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class QuizCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
//    @JsonProperty(value = "id")
    private QuizDao quizDao;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDao userDao;

    private LocalDateTime completedAt;

    public QuizCompletion() {
    }

    public QuizCompletion(QuizDao quizDao, UserDao userDao, LocalDateTime completedAt) {
        this.quizDao = quizDao;
        this.userDao = userDao;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizDao getQuizDao() {
        return quizDao;
    }

    public void setQuizDao(QuizDao quizDao) {
        this.quizDao = quizDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}
