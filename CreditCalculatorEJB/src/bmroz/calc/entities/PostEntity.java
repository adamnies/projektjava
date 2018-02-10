package bmroz.calc.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "post", schema = "jpdsi_calculator", catalog = "")
public class PostEntity {
    private int idPost;
    private Timestamp data;
    private String description;
    private String photoUrl;
    private UserEntity userByAuthorId;
    private UserEntity userByOwnerId;

    @Id
    @Column(name = "idPost")
    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    @Basic
    @Column(name = "data")
    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "photoUrl")
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return idPost == that.idPost &&
                Objects.equals(data, that.data) &&
                Objects.equals(description, that.description) &&
                Objects.equals(photoUrl, that.photoUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idPost, data, description, photoUrl);
    }

    @ManyToOne
    @JoinColumn(name = "authorId", referencedColumnName = "idUser", nullable = false)
    public UserEntity getUserByAuthorId() {
        return userByAuthorId;
    }

    public void setUserByAuthorId(UserEntity userByAuthorId) {
        this.userByAuthorId = userByAuthorId;
    }

    @ManyToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "idUser", nullable = false)
    public UserEntity getUserByOwnerId() {
        return userByOwnerId;
    }

    public void setUserByOwnerId(UserEntity userByOwnerId) {
        this.userByOwnerId = userByOwnerId;
    }
}
