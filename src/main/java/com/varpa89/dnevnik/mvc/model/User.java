package com.varpa89.dnevnik.mvc.model;

import com.varpa89.dnevnik.util.JsonDateDeserializer;
import com.varpa89.dnevnik.util.JsonDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.deser.std.DateDeserializer;
import org.codehaus.jackson.map.ser.std.DateSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 * User: varpa89
 * Date: 18.04.14
 * Time: 15:32
 */
@Entity
@Table(name = "ACCOUNT")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq")
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq")
    private Long id;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    private String middleName;

    @Basic
    private String login;

    @Basic
    private String password;

    @Transient
    private String password2;

    @Basic
    private String comment;

    @Basic
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Basic
    private Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @JsonIgnore
    public String getPassword2() {return password2;}

    @JsonProperty
    public void setPassword2(String password2) {this.password2 = password2;}

    @JsonSerialize(using = JsonDateSerializer.class, include=JsonSerialize.Inclusion.NON_NULL)
    public Date getBirthDate() {
        return birthDate;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @JsonIgnore
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonIgnore
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
