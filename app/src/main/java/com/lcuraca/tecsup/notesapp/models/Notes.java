package com.lcuraca.tecsup.notesapp.models;
import com.orm.dsl.Table;

@Table
public class Notes {

    private Long id, userId;
    private String title, content, state;

    public Notes(){
    }

    public Notes(Long userId, String title, String content, String state) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
