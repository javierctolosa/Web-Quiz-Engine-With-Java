package engine.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class QuizResultDao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
    boolean success;
    String feedback;

    public QuizResultDao() {}

    public QuizResultDao(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public QuizResultDao(Integer id, boolean success, String feedback) {
        this.id = id;
        this.success = success;
        this.feedback = feedback;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
