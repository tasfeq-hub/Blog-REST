package com.tasfeq.blog.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/* helpful for Lazy Loading child property 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
*/
@Entity
@Table(name="blogs")
public class Blog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String title;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category_id;
	
	@Column(nullable= true)
	private String image;
	
	@NotNull
	private Date created_at;
	
	@Enumerated(EnumType.STRING)
	private PublicationType status;
	
	public Blog() {}

	public Blog(Long id, @NotNull String title, @NotNull Category category_id, String image, @NotNull Date created_at,
			PublicationType status) {
		this(title,category_id,image,created_at,status);
		this.id = id;
	}
	
	public Blog(@NotNull String title, @NotNull Category category_id, String image, @NotNull Date created_at,
			PublicationType status) {
		this.title = title;
		this.category_id = category_id;
		this.image = image;
		this.created_at = created_at;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Category category_id) {
		this.category_id = category_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public PublicationType getStatus() {
		return status;
	}

	public void setStatus(PublicationType status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", category_id=" + category_id + ", image=" + image
				+ ", created_at=" + created_at + ", status=" + status + "]";
	}
	
}
