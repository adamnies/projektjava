package bmroz.calc.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "jpdsi_calculator", catalog = "")
public class UserEntity {
    private int idUser;
    private String email;
    private String password;
    private String profileImage;
    private String description;
    private String city;
    private String username;
    private Collection<PostEntity> postsWhereUserIsAuthor;
    private Collection<PostEntity> postsWhereUserIsOwner;

    @Id
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "profileImage")
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return idUser == that.idUser &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(profileImage, that.profileImage) &&
                Objects.equals(description, that.description) &&
                Objects.equals(city, that.city) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idUser, email, password, profileImage, description, city, username);
    }

    @OneToMany(mappedBy = "userByAuthorId")
    public Collection<PostEntity> getPostsWhereUserIsAuthor() {
        return postsWhereUserIsAuthor;
    }

    public void setPostsWhereUserIsAuthor(Collection<PostEntity> postsWhereUserIsAuthor) {
        this.postsWhereUserIsAuthor = postsWhereUserIsAuthor;
    }

    @OneToMany(mappedBy = "userByOwnerId")
    public Collection<PostEntity> getPostsWhereUserIsOwner() {
        return postsWhereUserIsOwner;
    }

    public void setPostsWhereUserIsOwner(Collection<PostEntity> postsWhereUserIsOwner) {
        this.postsWhereUserIsOwner = postsWhereUserIsOwner;
    }
}
