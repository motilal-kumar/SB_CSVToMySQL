package com.demoApp.demo.entity;


;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "content")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

	  @Id
	  @Column(name = "id")
	  private long id;

	 /* @Column(name = "title")
	  private String title;

	  @Column(name = "email")
	  private String email;*/

	  @Column(name = "type")
	  private String type;

	 /* @Column(name = "state")
	  private String state;

	  @Column(name = "author")
	  private String author;
*/
	  @Column(name = "description")
	  private String description;

	  /*@Column(name = "published")
	  private boolean published;
*/

	}
